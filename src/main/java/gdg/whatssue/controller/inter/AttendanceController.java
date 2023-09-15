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
    @PostMapping("/start")
    @Operation(tags = { "출석 관리" },
        summary = "출석체크 시작",
        description = "api 경로로 일정ID를 전달받아 해당 일정의 출석 시작, 출석 번호 반환")
    public ResponseEntity startAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석 종료
    @DeleteMapping("/")
    @Operation(tags = { "출석 관리" },
        summary = "출석체크 종료",
        description = "api 경로로 일정ID를 전달받아 해당 일정의 출석 종료")
    public ResponseEntity finishAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석 재시작
    @PatchMapping("/start")
    @Operation(tags = { "출석 관리" },
        summary = "출석체크 재시작(연장)",
        description = "api 경로로 일정ID를 전달받아 해당 일정의 출석 재시작(연장), 출석 번호 반환")
    public ResponseEntity restartAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석 결과 조회
    @GetMapping("/result")
    @Operation(tags = { "출석 관리" },
        summary = "출석 결과 조회",
        description = "api 경로로 일정ID를 전달받아 해당 일정의 출석결과(출/공/결) 반환")
    public ResponseEntity getAttendanceResult(@PathVariable Long scheduleId) throws NotImplementedException;

    /**
     * Member api
    **/

    //출석 열기
    @GetMapping("/check")
    @Operation(tags = { "출석 관리" },
        summary = "출석체크 확인하기(유저 기준)",
        description = "api 경로로 일정ID를 전달받아 해당 일정의 출석이 진행중이라면 true 반환")
    public ResponseEntity openAttendance(@PathVariable Long scheduleId) throws NotImplementedException;

    //출석하기
    @PostMapping("/check")
    @Operation(tags = { "출석 관리" },
        summary = "출석체크 하기(유저 기준)",
        description = "api 경로로 일정ID를 전달받아 해당 일정에 출석")
    public ResponseEntity doAttendance(@PathVariable Long scheduleId) throws NotImplementedException;
}
