package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyOfficialAbsent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyOfficialAbsentId;

    private String absentReason;
    private LocalDate absentDate;
    private String absentIsAccepted;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void saveSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void saveMember(Member member) {
        this.member = member;
    }
    public void AcceptAbsent(String absentIsAccepted) {
        this.absentIsAccepted = absentIsAccepted;
    }

    public void saveIsAccepted(String absentIsAccepted) {
        this.absentIsAccepted = absentIsAccepted;
    }
}