package gdg.whatssue.service;

import gdg.whatssue.entity.Claim;
import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.MoneyBook;
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
        //club 와 bookID가 moneybook 테이블에 존재하는지 확인
        Optional<Club> optionalClub = clubRepository.findById(clubId);

        if (optionalClub.isPresent()) {
            Club club = optionalClub.get();

            //모든 입출금 내역의 totalPaidAmount 값 조정
            List<MoneyBook> allMoneyBooks = moneyBookRepository.findAllByClub(club);

            for(MoneyBook book : allMoneyBooks){
                BigDecimal previousTotalPaidAmount = book.getTotalPaidAmount();
                BigDecimal previousAmount = book.getBookAmount();
                if(book.getMoneyBookId().equals(bookId)) {
                    // 현재 업데이트 중인 Moneybook에 대한 처리입니다.
                    book.setBookTitle(accountBookCreateDto.getBookTitle());
                    book.setBookAmount(new BigDecimal(accountBookCreateDto.getBookAmount()));
                }
                previousTotalPaidAmount = previousTotalPaidAmount.subtract(previousAmount).add(book.getBookAmount());
                book.setTotalPaidAmount(previousTotalPaidAmount);

            }

            //수정
            moneyBookRepository.saveAll(allMoneyBooks);
            return ResponseEntity.ok("입출금 내역 수정 완료");

        } else {
            // 클럽이 없을 경우에 대한 처리를 추가할 수 있음
            // 예: ResponseEntity를 사용하여 클럽이 없음을 반환하거나 다른 응답을 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("클럽을 찾을 수 없습니다.");
        }
    }


    @Transactional
    public ResponseEntity deleteBook(Long bookId) {
        Long clubId = 1L;
        //bookID가 moneybook 테이블에 존재하는지 확인
        //club 와 bookID가 moneybook 테이블에 존재하는지 확인
        Optional<Club> optionalClub = clubRepository.findById(clubId);

        if (optionalClub.isPresent()) {
            Club club = optionalClub.get();
            //삭제할 금액을 가져온다.
            MoneyBook moneyBook = moneyBookRepository.findByMoneyBookId(bookId).orElse(null);
            try {

                //삭제된 금액만큼 모든 입출금 내역들의 totalPaidAmount 값 조정
                List<MoneyBook> allMoneyBooks = moneyBookRepository.findAllByClub(club);

                for (MoneyBook moneyBook1 : allMoneyBooks) {

                    if (!moneyBook1.getMoneyBookId().equals(bookId)) {
                        BigDecimal currentTotalAmount = moneyBook1.getTotalPaidAmount();
                        currentTotalAmount = currentTotalAmount.subtract(moneyBook.getBookAmount());

                        if (currentTotalAmount.compareTo(BigDecimal.ZERO) < 0) {
                            currentTotalAmount = BigDecimal.ZERO;
                        }

                        moneyBook.setTotalPaidAmount(currentTotalAmount);

                    }
                }
                moneyBookRepository.deleteByClub(club);
                moneyBookRepository.deleteById(bookId);

                return ResponseEntity.ok("입출금 내역 삭제 완료");
            } catch (EmptyResultDataAccessException e) {
                return ResponseEntity.badRequest().body("존재하지 않는 데이터 입니다.");
            }
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("클럽을 찾을 수 없습니다.");
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
