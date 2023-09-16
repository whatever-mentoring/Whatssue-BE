package gdg.whatssue.controller.inter;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping("/api/user")
public interface UserController {
    
    //회원가입

    ResponseEntity userSignUp() throws NotImplementedException;

    //아이디 중복 체크

    ResponseEntity checkIdDuplicate() throws NotImplementedException;

    // 로그인

    ResponseEntity userLogin() throws NotImplementedException;
}
