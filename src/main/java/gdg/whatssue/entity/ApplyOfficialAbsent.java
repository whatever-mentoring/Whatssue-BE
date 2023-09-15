package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplyOfficialAbsent {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long applyOfficialAbsentId;
    private String absentReason;
    private LocalDate absentDate;
    private Boolean absentIsAccepted;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    public void saveSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
