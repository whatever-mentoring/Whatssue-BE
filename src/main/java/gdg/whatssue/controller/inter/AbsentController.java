package gdg.whatssue.controller.inter;

import gdg.whatssue.service.dto.AbsentRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/schedule")
public interface AbsentController {

    //공결신청

    @PostMapping("/{scheduleId}/absent-request")
    @Operation(tags = { "공결 관리" },
        summary = "공결 신청",
        description = "api 경로로 일정ID를 전달받아 해당 일정에 대한 공결 요청")

    public ResponseEntity requestAbsent(@PathVariable Long scheduleId, @RequestBody AbsentRequestDto absentRequestDto) throws NotImplementedException;


    /**
     * Admin api
     **/

    //공결 조회
    @GetMapping("/absent/list")
    @Operation(tags = { "공결 관리" },
        summary = "공결 요청 조회",
        description = "현재 요청되어 있는 모든 공결을 조회")
    public ResponseEntity getAbsentRequest() throws NotImplementedException;

    //공결 수락
    @PostMapping("/absent-accept/{absentId}")
    @Operation(tags = { "공결 관리" },
        summary = "공결 요청 수락",
        description = "api 경로로 공결요청 ID를 전달받아 해당 공결 요청을 수락")
    ResponseEntity acceptAbsentRequest(@PathVariable Long absentId) throws NotImplementedException;

    //공결 거절
    @PostMapping("/absent-refuse/{absentId}")
    @Operation(tags = { "공결 관리" },
        summary = "공결 요청 거절",
        description = "api 경로로 공결요청 ID를 전달받아 해당 공결 요청을 거절")
    ResponseEntity refuseAbsentRequest(@PathVariable Long absentId) throws NotImplementedException;
}
