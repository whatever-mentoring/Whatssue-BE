package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
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

    @ManyToOne
    @JoinColumn(name="club_id")
    private Club club;

    @Builder
    public AttendanceByUserBySchedule(String attendanceType, Member member, Schedule schedule){
        this.member = member;
        this.schedule = schedule;
        this.attendanceType= attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public void changeAttendance(String absentReason) {
        this.attendanceType = absentReason;
    }

}
