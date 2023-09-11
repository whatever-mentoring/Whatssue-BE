package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.ScheduleController;
import gdg.whatssue.mockdata.Schedule;
import gdg.whatssue.mockdata.ScheduleByMonth;
import java.util.ArrayList;
import java.util.List;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleControllerImpl implements ScheduleController {

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
        //"TODO: This api should be implemented"
        Schedule mockData = Schedule.builder()
            .scheduleTitle("gdg 회의")
            .scheduleContent("다같이 회의합니다")
            .scheduleDate("2023-09-11")
            .scheduleTime("13:34")
            .isChecked(true).build();

        return ResponseEntity.status(200).body(mockData);
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
