package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class ApplyOfficialAbsent {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long applyOfficialAbsentId;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

}
