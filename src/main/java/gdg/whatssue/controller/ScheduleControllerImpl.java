package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.ScheduleController;
import gdg.whatssue.mockdata.Schedule;
import gdg.whatssue.mockdata.ScheduleByMonth;
import gdg.whatssue.service.ScheduleService;
import gdg.whatssue.service.dto.ScheduleByDateDto;
import gdg.whatssue.service.dto.ScheduleByMonthDto;
import gdg.whatssue.service.dto.ScheduleDetailDto;
import java.util.ArrayList;
import java.util.List;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleControllerImpl implements ScheduleController {

    private final ScheduleService scheduleService;

    @Override
    @GetMapping("/list/month:{year-month}")
    public ResponseEntity getScheduleByMonth(String yearMonth) {

        List<ScheduleByMonthDto> scheduleListByMonth = scheduleService.getScheduleByMonth(yearMonth);

        return ResponseEntity.status(HttpStatus.OK).body(scheduleListByMonth);
    }

    @Override
    @GetMapping("/list/date:{year-month-date}")
    public ResponseEntity getScheduleByDate(String yearMonthDate) {
        List<ScheduleByDateDto> scheduleListByDate = scheduleService.getScheduleByDate(yearMonthDate);

        return ResponseEntity.status(HttpStatus.OK).body(scheduleListByDate);
    }

    @Override
    @GetMapping("/{scheduleId}")
    public ResponseEntity getSchedule(Long scheduleId) {
        ScheduleDetailDto detailDto = scheduleService.getSchedule(scheduleId);

        if (detailDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 일정입니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(detailDto);
    }

    @Override
    @PatchMapping("/{scheduleId}")
    public ResponseEntity updateSchedule(Long scheduleId) throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    @Override
    @PostMapping("")
    public ResponseEntity createSchedule(@RequestBody ScheduleDetailDto dto) {
        scheduleService.createSchedule(dto);
        return ResponseEntity.status(HttpStatus.OK).body("일정이 등록되었습니다.");
    }

    @Override
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity deleteSchedule(Long scheduleId) throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }
}
