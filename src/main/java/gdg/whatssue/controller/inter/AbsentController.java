package gdg.whatssue.controller.inter;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping("/api/schedule")
public interface AbsentController {

    //공결신청
    @PostMapping("/{scheduleId}/absent-request")
    public ResponseEntity requestAbsent(@PathVariable Long scheduleId) throws NotImplementedException;

    /**
     * Admin api
     **/

    //공결 조회
    @GetMapping("/absent/list")
    public ResponseEntity getAbsentRequest() throws NotImplementedException;

    //공결 수락
    @PostMapping("/absent-accept/{absentId}")
    public ResponseEntity acceptAbsentRequest(@PathVariable Long absentId) throws NotImplementedException;

    //공결 거절
    @PostMapping("/absent-refuse/{absentId}")
    public ResponseEntity refuseAbsentRequest(@PathVariable Long absentId) throws NotImplementedException;
}
