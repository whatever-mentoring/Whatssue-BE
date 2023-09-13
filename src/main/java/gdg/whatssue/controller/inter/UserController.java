package gdg.whatssue.controller.inter;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping("/api/user")
public interface UserController {
    
    //회원가입
    @PostMapping("/sign-up")
    public ResponseEntity userSignUp() throws NotImplementedException;

    //아이디 중복 체크
    @PostMapping("/sign-up/checkId")
    public ResponseEntity checkIdDuplicate() throws NotImplementedException;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity userLogin() throws NotImplementedException;
}
