package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.AbsentController;
import gdg.whatssue.entity.ApplyOfficialAbsent;
import gdg.whatssue.service.AbsentService;
import gdg.whatssue.service.dto.AbsentRequestDto;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AbsentControllerImpl implements AbsentController {

    private final AbsentService absentService;

    @Override
    public ResponseEntity requestAbsent(Long scheduleId, AbsentRequestDto absentRequestDto) throws NotImplementedException {
        return absentService.requestAbsent(scheduleId,absentRequestDto);
    }

    @Override
    public ResponseEntity getAbsentRequest() throws NotImplementedException {
        //공결 신청 테이블 조회
        return absentService.getAbsentRequest();

    }

    @Override
    public ResponseEntity acceptAbsentRequest(Long absentId) throws NotImplementedException {
        return absentService.acceptAbsentRequest(absentId);
    }

    @Override
    public ResponseEntity refuseAbsentRequest(Long absentId) throws NotImplementedException {
        return absentService.refuseAbsentRequest(absentId);
    }
}
