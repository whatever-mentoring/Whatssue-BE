package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ApplyOfficialAbsent {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long applyOfficialAbsentId;
    private String absentReason;
    private LocalDate absentDate;
    private Boolean isAccepted;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

}
