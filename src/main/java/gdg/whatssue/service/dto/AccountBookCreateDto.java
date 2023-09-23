package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AccountBookCreateDto {
    private String bookTitle; // 입출금 제목
    private String bookAmount;

    @Builder
    public AccountBookCreateDto(String bookTitle, String bookAmount) {
        this.bookTitle = bookTitle;
        this.bookAmount = bookAmount;
    }
}
