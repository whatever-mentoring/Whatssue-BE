package gdg.whatssue.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "정산 청구 DTO")
public class AccountClaimDto {
    @Schema(description = "청구 이름", example = "엠티 비용 정산")
    private String ClaimName;
    @Schema(description = "청구 내역", example = "18500.0")
    private String ClaimAmount;
    @Schema(description = "청구 날짜", example = "2023-10-21")
    private String ClaimDate;

    @Builder
    public AccountClaimDto(String ClaimName, String ClaimAmount, String ClaimDate) {
        this.ClaimName = ClaimName;
        this.ClaimAmount = ClaimAmount;
        this.ClaimDate = ClaimDate;
    }
}
