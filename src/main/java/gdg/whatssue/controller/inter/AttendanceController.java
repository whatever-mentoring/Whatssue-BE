package gdg.whatssue.controller.inter;

import gdg.whatssue.service.dto.CheckNumDto;
import io.swagger.v3.oas.annotations.Operation;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/schedule/{scheduleId}/attendance")
public interface AttendanceController {

    /**
     * Admin api
    **/
    //출석 시작

    ResponseEntity startAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석 종료

    ResponseEntity finishAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석 재시작

    ResponseEntity restartAttendance(@PathVariable Long scheduleId) throws Exception;

    //출석 결과 조회

    ResponseEntity getAttendanceResult(@PathVariable Long scheduleId) throws NotImplementedException;

    /**
     * Member api
    **/

    //출석 열기

    ResponseEntity openAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석하기
    ResponseEntity doAttendance(@PathVariable Long scheduleId, @RequestBody CheckNumDto dto) throws Exception;
}
