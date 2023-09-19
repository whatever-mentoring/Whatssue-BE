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





@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
@Tag(name = "Attendance", description = "출석 관리")
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
    @Operation(tags = { "출석 종료" },
            summary = "출석 종료 api",
            description = "관리자가 출석을 종료")
    @DeleteMapping("/{scheduleId}/attendance")
    public ResponseEntity<?> finishAttendance(Long scheduleId) throws NotImplementedException {
        return attendanceService.finishAttendance(scheduleId);
    }
    // 출석 재시작
    @Override
    @Operation(tags = { "출석 재시작" },
            summary = "출석 재시작 api",
            description = "관리자가 출석을 재시작")
    @PatchMapping("/{scheduleId}/attendance/start")
    public ResponseEntity<?> restartAttendance(Long scheduleId) throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    // 멤버 출석 리스트 조회
    @Override
    @Operation(tags = { "멤버 출석 조회" },
            summary = "일정에 대한 멤버들의 출석을 조회하는 api",
            description = "일정 조회")
    @GetMapping("/{scheduleId}/attendance/result")
    public ResponseEntity<?> getAttendanceResult(Long scheduleId) throws Exception{
        return attendanceService.getAttendanceResult(scheduleId);
    }
    //출석 열기(출석 시도)
    @Override
    @Operation(tags = { "멤버의 출석하기 시도" },
            summary = "멤버가 출석을 시도하는 api",
            description = "멤버가 출석을 시도하여 출석 중이면 출석 창으로 이동, 출석 중이 아니면 400 status")
    @GetMapping("/{scheduleId}/attendance/check")
    public ResponseEntity openAttendance(@PathVariable(name = "scheduleId") Long scheduleId) throws NotImplementedException {
        return attendanceService.openAttendance(scheduleId);
    }

    //출석하기(member)
    @Override
    @Operation(tags = { "멤버 출석하기" },
            summary = "멤버가 출석 인증 번호로 출석하는 api",
            description = "멤버가 출석 번호를 입력하여 출석 진행")
    @PostMapping("/{scheduleId}/attendance/check")
    public ResponseEntity<?> doAttendance(@PathVariable Long scheduleId, @RequestBody CheckNumDto dto) throws Exception {
        return attendanceService.doAttendance(scheduleId, dto.getNumber());
    }

}