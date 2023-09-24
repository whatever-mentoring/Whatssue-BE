package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.AccountingController;
import gdg.whatssue.service.AccountService;
import gdg.whatssue.service.dto.AccountBookCreateDto;
import gdg.whatssue.service.dto.AccountClaimDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account/")
@Tag(name = "Accounting", description = "정산 관련 api_회비 납부,정산 등")
public class AccountingControllerImpl implements AccountingController {
    private final AccountService accountService;

    @Override
    @Operation(
            summary = "정산 청구 api",
            description = "클럽에 소속된 모든 user에게 청구 신청")
    public ResponseEntity<?> claim(@RequestBody AccountClaimDto dto) throws Exception {
        log.info("정산 청구 api 호출");
        log.info(dto.getClaimDate());
        log.info(dto.getClaimAmount());
        log.info(dto.getClaimName());
        return accountService.createClaim(dto);
    }

    @Operation(
            summary = "정산 청구 리스트 api",
            description = "클럽에 소속된 모든 user에게 청구 신청"
    )
    public ResponseEntity<?> claimList() throws Exception {
        log.info("정산 청구 리스트 api 호출");
        Long clubId = 1L;
        return accountService.getClaimList(clubId);
    }

    @Override
    @Operation(
            summary = "입출금 내역 입력 api",
            description = "입출금 내역 입력")
    public ResponseEntity createBook(AccountBookCreateDto accountBookCreateDto) throws Exception {
        return accountService.createBook(accountBookCreateDto);
    }

    @Override
    @Operation(
            summary = "입출금 내역 전체 조회 api",
            description = "입출금 내역 전체 조회")
    public ResponseEntity getBookList() throws Exception {
        return accountService.getBookList();
    }

    @Override
    @Operation(
            summary = "입출금 내역 상세 조회 api",
            description = "입출금 내역 상세 조회")
    public ResponseEntity getBookDetail(Long bookId) throws Exception {
        return accountService.getBookDetail(bookId);
    }

    @Override
    @Transactional
    @Operation(
            summary = "입출금 내역 수정 api",
            description = "입출금 내역 수정")
    public ResponseEntity updateBook(Long bookId, @RequestBody AccountBookCreateDto accountBookCreateDto) throws Exception {
        return accountService.updateBook(bookId, accountBookCreateDto);
    }



    @Override
    @Operation(
            summary = "입출금 내역 삭제 api",
            description = "입출금 내역 삭제")
    public ResponseEntity deleteBook(Long bookId) throws Exception {
        return accountService.deleteBook(bookId);
    }



//    @PostMapping("/claim/{memberId}")
//    @Operation(
//            summary = "정산 청구 api",
//            description = "클럽에 소속된 특정 user에게 청구 신청")
//    public ResponseEntity<?> claim(Long memberId){return null;}
}

