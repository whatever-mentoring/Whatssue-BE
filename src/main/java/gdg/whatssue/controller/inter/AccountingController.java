package gdg.whatssue.controller.inter;

import gdg.whatssue.service.dto.AccountBookCreateDto;
import gdg.whatssue.service.dto.AccountClaimDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/account")
public interface AccountingController {

    @PostMapping("/claim")
    ResponseEntity<?> claim(@RequestBody AccountClaimDto dto) throws Exception;

    //입출금 내역 입력
    @PostMapping("/book/create")
    ResponseEntity createBook(@RequestBody AccountBookCreateDto accountBookCreateDto) throws Exception;

    //입출금 내역 전체 조회
    @GetMapping("/book/list")
    ResponseEntity getBookList() throws Exception;

    //입출금 내역 상세 조회
    @GetMapping("/book/list/{bookId}")
    ResponseEntity getBookDetail(@PathVariable Long bookId) throws Exception;

    //입출금 내역 수정
    @PatchMapping("/book/{bookId}")
    ResponseEntity updateBook(@PathVariable Long bookId,@RequestBody AccountBookCreateDto accountBookCreateDto) throws Exception;

    //입출금 내역 삭제
    @DeleteMapping("/book/{bookId}")
    ResponseEntity deleteBook(@PathVariable Long bookId) throws Exception;

}
