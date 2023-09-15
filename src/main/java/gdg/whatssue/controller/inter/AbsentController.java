package gdg.whatssue.controller.inter;

import io.swagger.v3.oas.annotations.Operation;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin

public interface AbsentController {

    //공결신청
    public ResponseEntity requestAbsent(@PathVariable Long scheduleId) throws NotImplementedException;

    /**
     * Admin api
     **/

    //공결 조회

    public ResponseEntity getAbsentRequest() throws NotImplementedException;

    //공결 수락

    public ResponseEntity acceptAbsentRequest(@PathVariable Long absentId) throws NotImplementedException;

    //공결 거절

    public ResponseEntity refuseAbsentRequest(@PathVariable Long absentId) throws NotImplementedException;
}
