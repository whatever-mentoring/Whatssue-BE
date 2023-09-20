package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.ScheduleController;
import gdg.whatssue.service.ScheduleService;
import gdg.whatssue.service.dto.ScheduleByDateDto;
import gdg.whatssue.service.dto.ScheduleByMonthDto;
import gdg.whatssue.service.dto.ScheduleDetailDto;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Schedule", description = "일정 관련 api")
public class ScheduleControllerImpl implements ScheduleController {

    private final ScheduleService scheduleService;

    @Override
    @Operation(
            summary = "월단위 일정 리스트 조회 api",
            description = "월단위 일정 리스트 조회")
    public ResponseEntity getScheduleByMonth(String yearMonth) {

        List<ScheduleByMonthDto> scheduleListByMonth = scheduleService.getScheduleByMonth(yearMonth);
        return ResponseEntity.status(HttpStatus.OK).body(scheduleListByMonth);
    }

    @Override
    @Operation(
            summary = "일단위 일정 리스트 조회 api",
            description = "일단위 일정 리스트 조회")
    public ResponseEntity getScheduleByDate(String yearMonthDate) {
        List<ScheduleByDateDto> scheduleListByDate = scheduleService.getScheduleByDate(yearMonthDate);

        return ResponseEntity.status(HttpStatus.OK).body(scheduleListByDate);
    }

    @Override
    @Operation(
            summary = "일정 상세 조회 api",
            description = "일정 상세 조회")
    public ResponseEntity getSchedule(Long scheduleId) {
        ScheduleDetailDto detailDto = scheduleService.getSchedule(scheduleId);

        if (detailDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 일정입니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(detailDto);
    }

    @Override
    @Transactional
    @Operation(
            summary = "일정 수정 api",
            description = "일정 수정")
    public ResponseEntity updateSchedule(Long scheduleId,ScheduleDetailDto scheduleDetailDto) throws NotImplementedException {
        // "TODO: 권한 확인 추후 추가"

        return scheduleService.updateSchedule(scheduleId, scheduleDetailDto);
    }
    @Override
    @PostMapping("")
    @Operation(
            summary = "일정 생성 api",
            description = "일정 생성")
    public ResponseEntity createSchedule(@RequestBody ScheduleDetailDto dto) throws NotImplementedException{
        return scheduleService.createSchedule(dto);
    }

    @Override
    @Operation(
            summary = "일정 삭제 api",
            description = "일정 삭제")
    public ResponseEntity deleteSchedule(Long scheduleId) throws NotImplementedException {
        // "TODO: 권한 확인 추후 추가"
        return scheduleService.deleteSchedule(scheduleId);
    }
}
