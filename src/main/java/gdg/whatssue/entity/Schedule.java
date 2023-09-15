package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

}
