package gdg.whatssue.service;

import gdg.whatssue.entity.Claim;
import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.MoneyBook;
import gdg.whatssue.mapper.MoneyBookCreateMapper;
import gdg.whatssue.repository.ClaimRepository;
import gdg.whatssue.repository.ClubRepository;
import gdg.whatssue.repository.MoneyBookRepository;
import gdg.whatssue.service.dto.AccountBookCreateDto;
import gdg.whatssue.service.dto.AccountBookListDto;
import gdg.whatssue.service.dto.AccountClaimDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final ClaimRepository claimRepository;
    private final ClubRepository clubRepository;
    private final MoneyBookRepository moneyBookRepository;

    public ResponseEntity<?> createClaim(AccountClaimDto dto) throws Exception {
        Long clubId = 1L; // 1로 가정
        Club club = clubRepository.findById(clubId).orElseThrow(() -> (
                new ResponseStatusException(HttpStatus.NOT_FOUND, "클럽을 찾을 수 없습니다.")
        ));
        Claim claim = Claim.builder()
                .club(club)
                .isClosed(false)
                .claimDate(dto.getClaimDate())
                .claimAmount(new BigDecimal(dto.getClaimAmount()))
                .claimName(dto.getClaimName())
                .build();
        claimRepository.save(claim);
        return ResponseEntity.ok("정산 청구 완료");
    }


    //변경 (삭제, 입력) 이 일어날때마다 clubId에 해당하는 모든 TotalPaidAmount 가 같은 값을 가져야하는데 그게 안됨 (어려움)
    public ResponseEntity createBook(AccountBookCreateDto accountBookCreateDto) {
        Long clubId = 1L;
        Optional<Club> optionalClub = clubRepository.findById(clubId);
        BigDecimal  bookAmount = new BigDecimal(accountBookCreateDto.getBookAmount());
        BigDecimal totalAmount = new BigDecimal(0);
        //club 이 존재 할 경우
        if (optionalClub.isPresent()) {
            Club club = optionalClub.get();
            //MoneyBook moneyBook = MoneyBookCreateMapper.INSTANCE.toEntity(accountBookCreateDto);


            try {
                //기존에 clubId 와 일치하는 리스트 가져오기
                List<MoneyBook> moneyBookList = moneyBookRepository.findAllByClub(club);

                if (moneyBookList.size() != 0) {
                    //moneyBookList 에서 totalPaidAmount를 가져온다.
                    totalAmount = moneyBookList.get(0).getTotalPaidAmount();
                    //totalAmount 에 입력받은 bookamount 를 더해준다.
                    totalAmount = totalAmount.add(bookAmount);
                    System.out.println("totalAmount2 = " + totalAmount   );
                }
                else{
                    //totalAmount 에 입력받은 bookamount 를 더해준다.
                    totalAmount = bookAmount;
                    System.out.println("totalAmount3 = " + totalAmount   );
                }

                if(totalAmount.compareTo(BigDecimal.ZERO) < 0){
                    totalAmount = BigDecimal.ZERO;
                }
                //입출금 내역 생성
                for (MoneyBook book : moneyBookList) {
                    book.setTotalPaidAmount(totalAmount);
                }

                //builder 이용
                MoneyBook moneyBook = MoneyBook.builder()
                        .club(club)
                        //bookAmount 형변환
                        .bookAmount(new BigDecimal(accountBookCreateDto.getBookAmount()))
                        .bookTitle(accountBookCreateDto.getBookTitle())
                        //totalPaidAmount 는 clubId 가 같은 모든 bookAmount 의 합
                        .totalPaidAmount(totalAmount)
                        .build();

                moneyBookList.add(moneyBook);
                moneyBookList.forEach(money -> moneyBookRepository.save(money));

                return ResponseEntity.ok("입출금 내역 생성 완료");

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입출금 내역 생성 실패");
            }
        } else {
            // 클럽이 없을 경우에 대한 처리를 추가할 수 있음
            // 예: ResponseEntity를 사용하여 클럽이 없음을 반환하거나 다른 응답을 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("클럽을 찾을 수 없습니다.");
        }
    }


    @Transactional
    public ResponseEntity updateBook(Long bookId, AccountBookCreateDto accountBookCreateDto) {
        Long clubId = 1L;
        Optional<Club> optionalClub = clubRepository.findById(clubId);
        //bookId 와 일치하는 Moneybook 가져오기
        MoneyBook moneyBook = moneyBookRepository.findById(bookId).orElseThrow(() -> (
                new ResponseStatusException(HttpStatus.NOT_FOUND, "입출금 내역을 찾을 수 없습니다.")
        ));

        //이전의 bookAmount
        BigDecimal bookAmount = moneyBook.getBookAmount();
        //이전의 totalAmount
        BigDecimal totalAmount = moneyBook.getTotalPaidAmount();
        //BigDecimal 타입으로 변환
        BigDecimal updateAmount = new BigDecimal(accountBookCreateDto.getBookAmount());
        if(optionalClub.isPresent()){
            Club club = optionalClub.get();
            try{
                //clubId 와 일치하는 List 가져오기
                List<MoneyBook> moneyBookList = moneyBookRepository.findAllByClub(club);
                if(moneyBookList.size()!=0) {
                    //if BookAmount 가 양수일 경우
                    if (bookAmount.compareTo(BigDecimal.ZERO) > 0) {
                        //update 전의 totalAmount 에서 이전의 bookAmount 를 뺀다.
                        totalAmount = totalAmount.subtract(bookAmount);
                        //뺀다음에 update 할 값을 totalAmount 에 더해야겠지
                        totalAmount = totalAmount.add(updateAmount);
                    } else {
                        //update 전의 totalAmount 에서 update 후의 bookAmount 를 더한다.
                        totalAmount = totalAmount.add(bookAmount);
                        //뺀다음에 update 할 값을 totalAmount 에 더해야겠지
                        totalAmount = totalAmount.add(updateAmount);
                    }
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입출금 내역이 없습니다.");
                }

                //totalAmount 가 음수 일때
                /*
                if(totalAmount.compareTo(BigDecimal.ZERO) < 0){
                    totalAmount = BigDecimal.ZERO;
                }
                */


                //clubId와 일치하는 입출금내역의 totalAmount 를 update
                for(MoneyBook moneyBook2 : moneyBookList){
                    //현재 업데이트 중인 moneybook에 대한 처리
                    if(moneyBook.getMoneyBookId().equals(bookId)){
                        moneyBook.updateMoneyBook(accountBookCreateDto.getBookTitle(),updateAmount,club,totalAmount);
                    }
                    moneyBook2.setTotalPaidAmount(totalAmount);
                }

                return ResponseEntity.ok("update success");

            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find club");
        }
    }


    @Transactional
    public ResponseEntity deleteBook(Long bookId) {
        Long clubId = 1L;
        //bookID가 moneybook 테이블에 존재하는지 확인
        //club 와 bookID가 moneybook 테이블에 존재하는지 확인
        Optional<Club> optionalClub = clubRepository.findById(clubId);
        //삭제할 금액
        MoneyBook moneyBook = moneyBookRepository.findById(bookId).orElseThrow(() -> (
                new ResponseStatusException(HttpStatus.NOT_FOUND, "can't find moneybook")
        ));
        BigDecimal bookAmount = moneyBook.getBookAmount();
        BigDecimal totalAmount = moneyBook.getTotalPaidAmount();

        if(optionalClub.isPresent()) {
            try {
                Club club = optionalClub.get();


                List<MoneyBook> moneyBookList = moneyBookRepository.findAllByClub(club);
                if (moneyBookList.size() != 0 && club.getMoneyBook().equals(moneyBook)) {
                    //update 전의 totalAmount 에서 delete 하는 bookAmount 를 뺀다.
                    //if bookAmount 가 음수면 더해준다.
                    if(bookAmount.compareTo(BigDecimal.ZERO) < 0)
                        totalAmount = totalAmount.add(bookAmount);
                    else{
                        //if bookAmount 가 양수면 뺀다. (양수 - 양수 = 음수
                        totalAmount = totalAmount.subtract(bookAmount);
                    }
                    // 삭제할 Moneybook이 리스트에 있다면 제거
                    moneyBookList.removeIf(mb -> mb.getMoneyBookId().equals(bookId));

                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("don't find moneybook");
                }
                /*
                //totalAmount 가 0일때
                if (totalAmount.compareTo(BigDecimal.ZERO) < 0) {
                    totalAmount = BigDecimal.ZERO;
                }
                */

                //Club 엔티티에는 MoneyBook 객체에 대한 참조가 존재함.
                club.setMoneyBook(null); // 하...club 에서 moneybook 객체 정의해둔지 몰랐음ㅋㅋ
                moneyBookRepository.save(moneyBook);
                moneyBookRepository.delete(moneyBook);

                //clubId와 일치하는 입출금내역의 totalAmount 를 update
                for (MoneyBook moneyBook1 : moneyBookList) {
                    moneyBook1.setTotalPaidAmount(totalAmount);
                }

                return ResponseEntity.ok("delete success");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("delete fail");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("can't find club");

            }
    }


    public ResponseEntity getBookList() {
        Long clubId = 1L;
        Club club = clubRepository.findById(clubId).orElseThrow(() -> (
                new ResponseStatusException(HttpStatus.NOT_FOUND, "클럽을 찾을 수 없습니다.")
        ));

        //club 이 존재 할 경우
        if (club != null) {
            //입출금 내역 전체 조회
            List<MoneyBook> moneyBookList = moneyBookRepository.findAllByClub(club);

            //list entity -> list dto 로 매핑 stream 이용
            List<AccountBookListDto> accountBookListDtoList = moneyBookList.stream().map(MoneyBook -> AccountBookListDto.builder()
                    .moneyBookId(MoneyBook.getMoneyBookId())
                    .bookTitle(MoneyBook.getBookTitle())
                    .bookAmount(MoneyBook.getBookAmount().toString())
                    .totalPaidAmount(MoneyBook.getTotalPaidAmount().toString())
                    .createAt(MoneyBook.getCreatedAt())
                    .build()).toList();

            return ResponseEntity.ok(accountBookListDtoList);

        } else {
            // 클럽이 없을 경우에 대한 처리를 추가할 수 있음
            // 예: ResponseEntity를 사용하여 클럽이 없음을 반환하거나 다른 응답을 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("클럽을 찾을 수 없습니다.");
        }
    }

    public ResponseEntity getBookDetail(Long bookId) {
        Long clubId = 1L;
        //club 와 bookID가 moneybook 테이블에 존재하는지 확인
        MoneyBook moneyBook = moneyBookRepository.findByClubIdAndMoneyBookId(clubId, bookId).orElseThrow(() -> (
                new ResponseStatusException(HttpStatus.NOT_FOUND, "입출금 내역을 찾을 수 없습니다.")
        ));

        //club 이 존재 할 경우
        if (moneyBook != null) {
            //입출금 내역 상세 조회
            //entity -> dto 로 builder 매핑
            AccountBookListDto accountBookListDto = AccountBookListDto.builder()
                    .moneyBookId(moneyBook.getMoneyBookId())
                    .bookTitle(moneyBook.getBookTitle())
                    .bookAmount(moneyBook.getBookAmount().toString())
                    .totalPaidAmount(moneyBook.getTotalPaidAmount().toString())
                    .createAt(moneyBook.getCreatedAt())
                    .build();


            return ResponseEntity.ok(accountBookListDto);

        } else {
            // 클럽이 없을 경우에 대한 처리를 추가할 수 있음
            // 예: ResponseEntity를 사용하여 클럽이 없음을 반환하거나 다른 응답을 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("클럽을 찾을 수 없습니다.");
        }
    }




}
