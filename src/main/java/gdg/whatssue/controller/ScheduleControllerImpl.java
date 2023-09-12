package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.ScheduleController;
import gdg.whatssue.mockdata.Schedule;
import gdg.whatssue.mockdata.ScheduleByMonth;
import gdg.whatssue.service.ScheduleService;
import gdg.whatssue.service.dto.ScheduleDetailDto;
import java.util.ArrayList;
import java.util.List;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleControllerImpl implements ScheduleController {

    private final ScheduleService scheduleService;

    @Override
    public ResponseEntity getScheduleByMonth(int month) {
        //"TODO: This api should be implemented"
        ScheduleByMonth mockData1 = ScheduleByMonth.builder()
            .scheduleId(1L)
            .scheduleTitle("일정1")
            .scheduleDate("2023-09-05").build();
        ScheduleByMonth mockData2 = ScheduleByMonth.builder()
            .scheduleId(4L)
            .scheduleTitle("일정2")
            .scheduleDate("2023-09-07").build();
        ScheduleByMonth mockData3 = ScheduleByMonth.builder()
            .scheduleId(5L)
            .scheduleTitle("일정3")
            .scheduleDate("2023-09-09").build();

        List<ScheduleByMonth> mockDataList = new ArrayList<>();
        mockDataList.add(mockData1);
        mockDataList.add(mockData2);
        mockDataList.add(mockData3);

        return ResponseEntity.status(200).body(mockDataList);
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
    public ResponseEntity updateSchedule(Long scheduleId) throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    @Override
    public ResponseEntity createSchedule() throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    @Override
    public ResponseEntity deleteSchedule(Long scheduleId) throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }
}
