package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.AbsentController;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AbsentControllerImpl implements AbsentController {


    @Override
    public ResponseEntity requestAbsent(Long scheduleId) throws NotImplementedException {
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
