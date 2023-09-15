package gdg.whatssue.controller.inter;

import io.swagger.v3.oas.annotations.Operation;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping("/api/schedule/{scheduleId}/attendance")
public interface AttendanceController {

    /**
     * Admin api
    **/
    //출석 시작

    public ResponseEntity startAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석 종료

    public ResponseEntity finishAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석 재시작

    public ResponseEntity restartAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석 결과 조회

    public ResponseEntity getAttendanceResult(@PathVariable Long scheduleId) throws NotImplementedException;

    /**
     * Member api
    **/

    //출석 열기

    public ResponseEntity openAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석하기
    public ResponseEntity doAttendance(@PathVariable Long scheduleId) throws NotImplementedException;
}
