package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class AttendanceByUserBySchedule {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long attendanceByUserId;

    private String attendanceType; // 출석, 결석, 공결

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;



}
