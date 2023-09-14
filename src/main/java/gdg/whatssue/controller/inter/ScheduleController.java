package gdg.whatssue.controller.inter;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping("/api/schedule")
public interface ScheduleController {

    //특정 일자의 일정들 조회
    //이 api는 배제, 월별 일정 조회 api 사용
    /*
    @GetMapping("/list/{date}")
    public ResponseEntity getScheduleListOnDate() throws NotImplementedException;
*/


    //전체 일정 조회
    //이 api는 배제, 월별 일정 조회 api 사용
    /*
    @GetMapping("/list")
    public ResponseEntity getScheduleList() throws NotImplementedException;
*/
    
    //월별일정 조회 api 추가
    @GetMapping("/list/month:{yearMonth}")
    public ResponseEntity getScheduleByMonth(@PathVariable String yearMonth) throws NotImplementedException;

    //일자별 일정 조회 api
    @GetMapping("/list/date:{yearMonthDate}")
    public ResponseEntity getScheduleByDate(@PathVariable String yearMonthDate) throws NotImplementedException;

    //세부 일정 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity getSchedule(@PathVariable Long scheduleId) throws NotImplementedException;

    /**
     * Admin api
     **/

    //일정 수정
    @PatchMapping("/{scheduleId}")
    public ResponseEntity updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleDetailDto scheduleDetailDto) throws NotImplementedException;

    //일정 추가
    @PostMapping("/")
    public ResponseEntity createSchedule() throws NotImplementedException;

    //일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity deleteSchedule(@PathVariable Long scheduleId) throws NotImplementedException;
}
