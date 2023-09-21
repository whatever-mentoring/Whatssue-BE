package gdg.whatssue.service;

import gdg.whatssue.entity.Claim;
import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.MoneyBook;
import gdg.whatssue.mapper.MoneyBookMapper;
import gdg.whatssue.repository.ClaimRepository;
import gdg.whatssue.repository.ClubRepository;
import gdg.whatssue.repository.MoneyBookRepository;
import gdg.whatssue.service.dto.AccountBookCreateDto;
import gdg.whatssue.service.dto.AccountBookListDto;
import gdg.whatssue.service.dto.AccountClaimDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
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


    public ResponseEntity createBook(AccountBookCreateDto accountBookCreateDto) {
        Long clubId = 1L;
        Club club = clubRepository.findById(clubId).orElseThrow(() -> (
                new ResponseStatusException(HttpStatus.NOT_FOUND, "클럽을 찾을 수 없습니다.")
        ));

        //club 이 존재 할 경우
        if(club != null){
            //입출금 내역 생성
            //moneybookmapper를 이용해 dto -> entity로 변환
            MoneyBook moneyBook = MoneyBookMapper.INSTANCE.mapDTOToEntity(accountBookCreateDto);
            moneyBookRepository.save(moneyBook);
            return ResponseEntity.ok("입출금 내역 생성 완료");
        }
        // 클럽이 없을 경우에 대한 처리를 추가할 수 있음
        // 예: ResponseEntity를 사용하여 클럽이 없음을 반환하거나 다른 응답을 반환
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("클럽을 찾을 수 없습니다.");
    }

    public ResponseEntity getBookList() {
        Long clubId = 1L;
        Club club = clubRepository.findById(clubId).orElseThrow(() -> (
                new ResponseStatusException(HttpStatus.NOT_FOUND, "클럽을 찾을 수 없습니다.")
        ));

        //club 이 존재 할 경우
        if(club != null){
            //입출금 내역 전체 조회
            List<MoneyBook> moneyBookList = moneyBookRepository.findAllByClubId(clubId);

            //list entity -> list dto 로 매핑 stream 이용
            List<AccountBookListDto> accountBookListDtoList = moneyBookList.stream()
                    .map(moneyBook -> MoneyBookMapper.INSTANCE.mapEntityToDTO(moneyBook))
                    .toList();

            return ResponseEntity.ok(accountBookListDtoList);

        }else{
            // 클럽이 없을 경우에 대한 처리를 추가할 수 있음
            // 예: ResponseEntity를 사용하여 클럽이 없음을 반환하거나 다른 응답을 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("클럽을 찾을 수 없습니다.");
        }
    }

    public ResponseEntity getBookDetail(Long bookId) {
        Long clubId = 1L;
        Club club = clubRepository.findById(clubId).orElseThrow(() -> (
                new ResponseStatusException(HttpStatus.NOT_FOUND, "클럽을 찾을 수 없습니다.")
        ));

        //club 이 존재 할 경우
        if(club != null){
            //입출금 내역 상세 조회
            MoneyBook moneyBook = moneyBookRepository.findByMoneyBookId(bookId).orElseThrow(() -> (
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "입출금 내역을 찾을 수 없습니다.")
            ));

            //entity -> dto 로 매핑
            AccountBookListDto accountBookListDto = MoneyBookMapper.INSTANCE.mapEntityToDTO(moneyBook);

            return ResponseEntity.ok(accountBookListDto);

        }else{
            // 클럽이 없을 경우에 대한 처리를 추가할 수 있음
            // 예: ResponseEntity를 사용하여 클럽이 없음을 반환하거나 다른 응답을 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("클럽을 찾을 수 없습니다.");
        }
    }

    public ResponseEntity updateBook(Long bookId, AccountBookCreateDto accountBookCreateDto) {
        Long clubId = 1L;
        Club club = clubRepository.findById(clubId).orElseThrow(() -> (
                new ResponseStatusException(HttpStatus.NOT_FOUND, "클럽을 찾을 수 없습니다.")
        ));
        //club 이 존재 할 경우
        if(club != null) {
            //입출금 내역 수정
            MoneyBook moneyBook = moneyBookRepository.findByMoneyBookId(bookId).orElseThrow(() -> (
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "입출금 내역을 찾을 수 없습니다.")
            ));

            //dto -> entity 로 매핑
            MoneyBook updateMoneyBook = MoneyBookMapper.INSTANCE.mapDTOToEntity(accountBookCreateDto);

            //수정
            moneyBook.builder()
                    .moneyBookId(updateMoneyBook.getMoneyBookId())
                    .club(updateMoneyBook.getClub())
                    .bookTitle(updateMoneyBook.getBookTitle())
                    .bookAmount(updateMoneyBook.getBookAmount())
                    .totalPaidAmount(updateMoneyBook.getTotalPaidAmount())
                    .build();
            return ResponseEntity.ok("입출금 내역 수정 완료");

        }
        else{
            // 클럽이 없을 경우에 대한 처리를 추가할 수 있음
            // 예: ResponseEntity를 사용하여 클럽이 없음을 반환하거나 다른 응답을 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("클럽을 찾을 수 없습니다.");
        }
    }

    public ResponseEntity deleteBook(Long bookId) {
        Long clubId = 1L;
        Club club = clubRepository.findById(clubId).orElseThrow(() -> (
                new ResponseStatusException(HttpStatus.NOT_FOUND, "클럽을 찾을 수 없습니다.")
        ));
        //club 이 존재 할 경우
        if(club != null) {
            //입출금 내역 삭제
            MoneyBook moneyBook = moneyBookRepository.findByMoneyBookId(bookId).orElseThrow(() -> (
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "입출금 내역을 찾을 수 없습니다.")
            ));

            moneyBookRepository.delete(moneyBook);
            return ResponseEntity.ok("입출금 내역 삭제 완료");

        }
        else{
            // 클럽이 없을 경우에 대한 처리를 추가할 수 있음
            // 예: ResponseEntity를 사용하여 클럽이 없음을 반환하거나 다른 응답을 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("클럽을 찾을 수 없습니다.");
        }
    }
}
