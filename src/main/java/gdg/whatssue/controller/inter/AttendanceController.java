package gdg.whatssue.controller.inter;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/schedule/{scheduleId}/attendance")
public interface AttendanceController {

    /**
     * Admin api
    **/
    //출석 시작
    @PostMapping("/start")
    public void startAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석 종료
    @DeleteMapping("/")
    public void finishAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석 재시작
    @PatchMapping("/start")
    public void restartAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석 결과 조회
    @GetMapping("/result")
    public void getAttendanceResult(@PathVariable Long scheduleId) throws NotImplementedException;

    /**
     * Member api
    **/

    //출석 열기
    @GetMapping("/check")
    public void openAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석하기
    @PostMapping("/check")
    public void doAttendance(@PathVariable Long scheduleId) throws NotImplementedException;
}
