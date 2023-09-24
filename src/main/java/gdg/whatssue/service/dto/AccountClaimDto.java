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
    private String claimName;
    @Schema(description = "청구 내역", example = "18500.0")
    private String claimAmount;
    @Schema(description = "청구 날짜", example = "2023-10-21")
    private String claimDate;

    @Builder
    public AccountClaimDto(String claimName, String claimAmount, String claimDate) {
        this.claimName = claimName;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
    }
}
