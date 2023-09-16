package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.AttendanceController;
import gdg.whatssue.service.AttendanceService;
import gdg.whatssue.service.dto.CheckNumDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Attendance", description = "출석 관리")
@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class AttendanceControllerImpl implements AttendanceController {

    private final AttendanceService attendanceService;

    //출석 시작
    @Override
    @Operation(tags = { "출석 관리" },
            summary = "출석 시작 api",
            description = "api 경로로 일정ID를 입력 받아 출석을 시작")
    @PostMapping("/{scheduleId}/attendance/start")
    public ResponseEntity<?> startAttendance(@PathVariable(name = "scheduleId") Long scheduleId) throws NotImplementedException {
        return ResponseEntity.ok(attendanceService.startAttendance(scheduleId));
    }
    // 출석 종료
    @Override
    @DeleteMapping("/{scheduleId}/attendance")
    public ResponseEntity<?> finishAttendance(Long scheduleId) throws NotImplementedException {
        return attendanceService.finishAttendance(scheduleId);
    }
    // 출석 재시작
    @Override
    @PatchMapping("/{scheduleId}/attendance/start")
    public ResponseEntity<?> restartAttendance(Long scheduleId) throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    // 멤버 출석 리스트 조회
    @Override
    @GetMapping("/{scheduleId}/attendance/result")
    public ResponseEntity<?> getAttendanceResult(Long scheduleId) throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }
    //출석 열기(출석 시도)
    @Override
    @GetMapping("/{scheduleId}/attendance/check")
    public ResponseEntity openAttendance(@PathVariable(name = "scheduleId") Long scheduleId) throws NotImplementedException {
        return attendanceService.openAttendance(scheduleId);
    }

    //출석하기(member)
    @Override
    @PostMapping("/{scheduleId}/attendance/check")
    public ResponseEntity<?> doAttendance(@PathVariable Long scheduleId, @RequestBody CheckNumDto dto) throws Exception {
        return attendanceService.doAttendance(scheduleId, dto.getNumber());
    }

}