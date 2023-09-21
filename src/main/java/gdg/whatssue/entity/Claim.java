package gdg.whatssue.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "claim")
@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    private String claimName;
    private Boolean isClosed;
    private BigDecimal claimAmount;
    private LocalDate claimDate;

    @OneToMany(mappedBy = "claim")
    private List<ClaimResult> claimResultList;

//    @OneToOne
//    @JoinColumn(name = "schedule_id", nullable = true)
//    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
    @Builder
    public Claim(String claimName, Boolean isClosed, BigDecimal claimAmount, Club club, String claimDate) throws Exception {
        this.claimName = claimName;
        this.isClosed = isClosed;
        this.claimAmount = claimAmount;
        this.club = club;
        try {
            this.claimDate = LocalDate.parse(claimDate);
        } catch (Exception e) {
            log.info("날짜 형식이 잘못되었습니다." + claimDate);
            throw new Exception("날짜 형식이 잘못되었습니다.");
        }
    }
}
