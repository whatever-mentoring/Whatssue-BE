package gdg.whatssue.controller.inter;

import gdg.whatssue.service.dto.AbsentRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/schedule")
public interface AbsentController {

    //공결신청
    @PostMapping("/{scheduleId}/absent-request")
    public ResponseEntity requestAbsent(@PathVariable Long scheduleId, @RequestBody AbsentRequestDto absentRequestDto) throws NotImplementedException;


    /**
     * Admin api
     **/

    //공결 조회
    @GetMapping("/absent/list")
    public ResponseEntity getAbsentRequest() throws NotImplementedException;

    //공결 수락
    @PostMapping("/absent-accept/{absentId}")
    ResponseEntity acceptAbsentRequest(@PathVariable Long absentId) throws NotImplementedException;

    //공결 거절
    @PostMapping("/absent-refuse/{absentId}")
    ResponseEntity refuseAbsentRequest(@PathVariable Long absentId) throws NotImplementedException;
}
