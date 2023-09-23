package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class AccountBookCreateDto {
    private String bookTitle; // 입출금 제목
    private String bookAmount;
    private String totalPaidAmount;
}
