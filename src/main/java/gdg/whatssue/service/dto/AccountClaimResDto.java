package gdg.whatssue.service.dto;


import gdg.whatssue.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "정산 청구 결과 멤버별 확인 DTO")
public class AccountClaimResDto {
    private Long claimResultId;
    private String memberName ;
    private String claimAmount;
    private boolean isPaid;

    @Builder
    public AccountClaimResDto(String memberName, String claimAmount, boolean isPaid, Long claimResultId){
        this.claimResultId = claimResultId;
        this.memberName = memberName;
        this.claimAmount = claimAmount;
        this.isPaid = isPaid;
    }
}
