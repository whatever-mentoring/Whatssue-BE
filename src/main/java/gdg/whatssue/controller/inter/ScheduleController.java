package gdg.whatssue.controller.inter;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/schedule")
public interface ScheduleController {

    //특정 일자의 일정들 조회
    @GetMapping("/list/{date}")
    public void getScheduleListOnDate() throws NotImplementedException;

    //전체 일정 조회
    @GetMapping("/list")
    public void getScheduleList() throws NotImplementedException;

    //세부 일정 조회
    @GetMapping("/{scheduleId}")
    public void getSchedule(@PathVariable Long scheduleId) throws NotImplementedException;

    /**
     * Admin api
     **/

    //일정 수정
    @PatchMapping("/{scheduleId}")
    public void updateSchedule(@PathVariable Long scheduleId) throws NotImplementedException;

    //일정 추가
    @PostMapping("/")
    public void createSchedule() throws NotImplementedException;

    //일정 삭제
    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId) throws NotImplementedException;
}
