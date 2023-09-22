package gdg.whatssue.service.dto;

import gdg.whatssue.entity.Club;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class AccountBookListDto {
    private Long moneyBookId;
    private String bookTitle; // 입출금 제목
    private String bookAmount;
    private String totalPaidAmount;
    private LocalDateTime createAt;
}
