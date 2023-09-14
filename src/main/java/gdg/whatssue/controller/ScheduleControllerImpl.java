package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.ScheduleController;
import gdg.whatssue.mockdata.Schedule;
import gdg.whatssue.mockdata.ScheduleByMonth;
import gdg.whatssue.service.ScheduleService;
import gdg.whatssue.service.dto.ScheduleByMonthDto;
import gdg.whatssue.service.dto.ScheduleDetailDto;
import java.util.ArrayList;
import java.util.List;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleControllerImpl implements ScheduleController {

    private final ScheduleService scheduleService;

    @Override
    public ResponseEntity getScheduleByMonth(String yearMonth) {

        List<ScheduleByMonthDto> scheduleListByMonth = scheduleService.getScheduleByMonth(yearMonth);

        return ResponseEntity.status(HttpStatus.OK).body(scheduleListByMonth);
    }

    @Override
    public ResponseEntity getSchedule(Long scheduleId) {
        ScheduleDetailDto detailDto = scheduleService.getSchedule(scheduleId);

        if (detailDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 일정입니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(detailDto);
    }

    @Override
    @Transactional
    public ResponseEntity updateSchedule(Long scheduleId,ScheduleDetailDto scheduleDetailDto) throws NotImplementedException {
        // "TODO: 권한 확인 추후 추가"

        return scheduleService.updateSchedule(scheduleId, scheduleDetailDto);
    }

    @Override
    public ResponseEntity createSchedule() throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    @Override
    public ResponseEntity deleteSchedule(Long scheduleId) throws NotImplementedException {
        // "TODO: 권한 확인 추후 추가"
        return scheduleService.deleteSchedule(scheduleId);
    }
}
