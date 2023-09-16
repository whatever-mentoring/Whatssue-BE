package gdg.whatssue.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate //변경한 필드만 대응
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long scheduleId;

    private String scheduleTitle;
    private String scheduleContent;
    private LocalDate scheduleDate;
    private LocalTime scheduleTime;
    @Column(columnDefinition = "boolean default false")
    private Boolean isChecked; // 출석 체크 여부

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToOne(mappedBy = "schedule")
    private ApplyOfficialAbsent applyOfficialAbsent;

    @OneToMany(mappedBy = "schedule")
    private List<AttendanceByUserBySchedule> attendanceByUserByScheduleList;


    @Builder // 생성자에 builder를 붙이면 필요없는 속성의 노출을 막을 수 있음.
    public Schedule(String scheduleTitle, String scheduleContent, String scheduleDate, String scheduleTime, Club club) {
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
        this.scheduleDate = LocalDate.parse(scheduleDate);
        this.scheduleTime = LocalTime.parse(scheduleTime);
        this.isChecked = false;
        this.club = club;
    }


    public void updateSchedule(String scheduleTitle, String scheduleContent, LocalDate scheduleDate, LocalTime scheduleTime) {
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
        this.isChecked = false;
    }

    public void setIsChecked(boolean b) {
    }
}