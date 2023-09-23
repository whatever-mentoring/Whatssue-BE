package gdg.whatssue.controller;

import gdg.whatssue.service.AccountService;
import gdg.whatssue.service.dto.AccountClaimDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
@Tag(name = "Accounting", description = "정산 관련 api_회비 납부,정산 등")
public class AccountingControllerImpl {
    private final AccountService accountService;
    @Operation(
            summary = "정산 청구 api",
            description = "클럽에 소속된 모든 user에게 청구 신청")
    @PostMapping("/claim")
    public ResponseEntity<?> claim(@RequestBody AccountClaimDto dto) throws Exception {
        log.info("정산 청구 api 호출");
        log.info(dto.getClaimDate());
        log.info(dto.getClaimAmount());
        log.info(dto.getClaimName());
        return accountService.createClaim(dto);
    }

    @Operation(
            summary = "정산 청구 리스트 get api",
            description = "클럽에 소속된 모든 user에게 청구한 내역 admin이 확인")
    @GetMapping("/claim")
    public ResponseEntity<?> getClaimList() throws Exception {
        log.info("정산 청구 리스트 api 호출");
        return accountService.getClaimList();
    }
//    @PostMapping("/claim/{memberId}")
//    @Operation(
//            summary = "정산 청구 api",
//            description = "클럽에 소속된 특정 user에게 청구 신청")
//    public ResponseEntity<?> claim(Long memberId){return null;}
}

