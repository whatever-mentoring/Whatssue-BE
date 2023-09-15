package gdg.whatssue.controller.inter;

import gdg.whatssue.service.dto.ScheduleDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import gdg.whatssue.service.dto.ScheduleDetailDto;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity getScheduleByMonth(@PathVariable String yearMonth) throws NotImplementedException;

    //일자별 일정 조회 api
    @GetMapping("/list/date:{yearMonthDate}")
    ResponseEntity getScheduleByDate(@PathVariable String yearMonthDate) throws NotImplementedException;

    //세부 일정 조회
    @GetMapping("/{scheduleId}")
    ResponseEntity getSchedule(@PathVariable Long scheduleId) throws NotImplementedException;

    /**
     * Admin api
     **/

    //일정 수정
    @PatchMapping("/{scheduleId}")
    @Operation(tags = { "일정 관리 및 조회" },
            summary = "일정 수정 api",
            description = "api 경로로 일정ID, Request Body로 일정 정보를 json 형태로 입력 받아 일정 정보를 수정")
    ResponseEntity updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleDetailDto scheduleDetailDto) throws NotImplementedException;


    //일정 추가
    @PostMapping("/")
    @Operation(tags = { "일정 관리 및 조회" },
            summary = "일정 추가 api",
            description = "Request Body로 json 형태로 일정 정보를 입력 받아 일정을 추가")
    ResponseEntity createSchedule(@RequestBody ScheduleDetailDto dto) throws NotImplementedException;


    //일정 삭제
    @DeleteMapping("/{scheduleId}")
    @Operation(tags = { "일정 관리 및 조회" },
            summary = "일정 추가 api",
            description = "api 경로로 일정ID를 넘겨 받아 해당 일정을 삭제")
    ResponseEntity deleteSchedule(@PathVariable Long scheduleId) throws NotImplementedException;
}