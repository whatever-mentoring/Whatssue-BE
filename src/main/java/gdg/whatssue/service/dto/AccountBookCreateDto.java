package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@Setter
public class AccountBookCreateDto {
    private String bookTitle; // 입출금 제목
    private String bookAmount;
    private String totalPaidAmount;
}
