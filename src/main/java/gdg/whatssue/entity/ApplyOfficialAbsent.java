package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ApplyOfficiaAbsent {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long applyOfficialAbsentId;

    @OneToOne
    
    private Schedule schedule;

}
