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
    @Schema(description = "청구 내역 이름", example = "김예진")
    private String ClaimName;
    @Schema(description = "청구 내역", defaultValue = "18500", example = "18500")
    private String ClaimAmount;
    @Schema(description = "청구 날짜", defaultValue = "2023-10-31", example = "2023-10-21")
    private String ClaimDate;

    @Builder
    public AccountClaimDto(String ClaimName, String ClaimAmount, String ClaimDate) {
        this.ClaimName = ClaimName;
        this.ClaimAmount = ClaimAmount;
        this.ClaimDate = ClaimDate;
    }
}
