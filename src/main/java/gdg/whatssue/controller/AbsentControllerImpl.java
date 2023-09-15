package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.AbsentController;
import gdg.whatssue.service.AbsentService;
import gdg.whatssue.service.dto.AbsentRequestDto;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AbsentControllerImpl implements AbsentController {

    private final AbsentService absentService;

    @Override
    public ResponseEntity requestAbsent(@PathVariable Long scheduleId,@RequestBody AbsentRequestDto absentRequestDto) throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    @Override
    public ResponseEntity getAbsentRequest() throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    @Override
    public ResponseEntity acceptAbsentRequest(Long absentId) throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    @Override
    public ResponseEntity refuseAbsentRequest(Long absentId) throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }
}
