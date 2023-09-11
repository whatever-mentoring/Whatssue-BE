package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ApplyOfficialAbsent {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long applyOfficialAbsentId;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

}
