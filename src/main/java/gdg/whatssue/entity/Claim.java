package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Entity
@RequiredArgsConstructor
public class Claim {

    @Id
    private Long claimId;

    private String claimName;
    private Boolean isClosed;
    private BigDecimal claimAmount;

    @OneToMany(mappedBy = "claim")
    private List<ClaimResult> claimResultList;

    @OneToOne
    @JoinColumn(name = "schedule_id", nullable = true)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;


}
