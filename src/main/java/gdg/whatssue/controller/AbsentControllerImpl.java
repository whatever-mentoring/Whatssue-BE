package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.AbsentController;
import gdg.whatssue.service.AbsentService;
import gdg.whatssue.service.dto.AbsentRequestDto;
import gdg.whatssue.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Absent", description = "공결 신청, 조회, 수락, 거절")
public class AbsentControllerImpl implements AbsentController {

    private final AbsentService absentService;

    @Override
    @Operation(
            summary = "공결 신청",
            description = "api 경로로 일정ID를 전달받아 해당 일정에 대한 공결 요청")
    public ResponseEntity requestAbsent(@PathVariable Long scheduleId, @RequestBody AbsentRequestDto absentRequestDto) throws NotImplementedException {
        //"TODO: This api should be implemented"
        return absentService.requestAbsent(scheduleId,absentRequestDto);
    }

    @Override
    @Operation(
            summary = "공결 요청 조회",
            description = "현재 요청되어 있는 모든 공결을 조회")
    public ResponseEntity getAbsentRequest() throws NotImplementedException {
        //"TODO: This api should be implemented"
        return absentService.getAbsentRequest();
    }

    @Override
    @Operation(
            summary = "공결 요청 수락",
            description = "api 경로로 공결요청 ID를 전달받아 해당 공결 요청을 수락")
    public ResponseEntity acceptAbsentRequest(Long absentId) throws NotImplementedException {
        //"TODO: This api should be implemented"
        return absentService.acceptAbsentRequest(absentId);
    }

    @Override
    @Operation(
            summary = "공결 요청 거절",
            description = "api 경로로 공결요청 ID를 전달받아 해당 공결 요청을 거절")
    public ResponseEntity refuseAbsentRequest(Long absentId) throws NotImplementedException {
        //"TODO: This api should be implemented"
        return absentService.refuseAbsentRequest(absentId);
    }
}
