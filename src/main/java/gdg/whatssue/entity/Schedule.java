package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate //변경한 필드만 대응
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long scheduleId;

    private String scheduleTitle;
    private String scheduleContent;
    private LocalDate scheduleDate;
    private LocalTime scheduleTime;
    private Boolean isChecked; // 출석 체크 여부

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToOne(mappedBy = "schedule")
    private ApplyOfficialAbsent applyOfficialAbsent;
    
    @OneToMany(mappedBy = "schedule")
    private List<AttendanceByUserBySchedule> attendanceByUserByScheduleList;

    public void updateSchedule(String scheduleTitle, String scheduleContent, LocalDate scheduleDate, LocalTime scheduleTime) {
         this.scheduleTitle = scheduleTitle;
         this.scheduleContent = scheduleContent;
         this.scheduleDate = scheduleDate;
         this.scheduleTime = scheduleTime;
    }
}
