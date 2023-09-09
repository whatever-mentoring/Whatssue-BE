package gdg.whatssue.controller.inter;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/schedule/{scheduleId}")
public interface AbsentController {

    //공결신청
    @PostMapping("/absent-request")
    public void requestAbsent(@PathVariable Long scheduleId) throws NotImplementedException;

    /**
     * Admin api
     **/

    //공결 조회
    @GetMapping("/absent")
    public void getAbsentRequest(@PathVariable Long scheduleId) throws NotImplementedException;

    //공결 수락
    @PostMapping("/absent-accept")
    public void acceptAbsentRequest(@PathVariable Long scheduleId) throws NotImplementedException;

    //공결 거절
    @PostMapping("/absent-refuse")
    public void refuseAbsentRequest(@PathVariable Long scheduleId) throws NotImplementedException;
}
