package gdg.whatssue.entity;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class ClaimResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ClaimResultId;

    @ManyToOne
    @JoinColumn(name = "claim_id")
    private Claim claim;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

//    private BigDecimal paidAmount;

    private Boolean isPaid; // 정산 완료 여부


    @Builder
    public ClaimResult(Claim claim, Member member, Boolean isPaid) {
        this.claim = claim;
        this.member = member;
        this.isPaid = isPaid;
    }



}
