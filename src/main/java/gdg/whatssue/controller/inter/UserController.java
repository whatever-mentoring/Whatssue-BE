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
@Tag(name = "User", description = "회원가입, 로그인, 아이디 중복 체크")
public interface UserController {
    
    //회원가입
    @PostMapping("/sign-up")
    @Operation(tags = { "회원 관리" },
        summary = "회원가입 api",
        description = "Request Body로 json 형태로 회원가입 정보를 전달 받아 회원 추가")
    public ResponseEntity userSignUp() throws NotImplementedException;

    //아이디 중복 체크
    @PostMapping("/sign-up/checkId")
    @Operation(tags = { "회원 관리" },
        summary = "id 중복체크 api",
        description = "Request Body로 json 형태로 id를 전달 받아 id 중복체크")
    public ResponseEntity checkIdDuplicate() throws NotImplementedException;

    // 로그인
    @PostMapping("/login")
    @Operation(tags = { "회원 관리" },
        summary = "회원 로그인 api",
        description = "Request Body로 json 형태로 회원로그인 정보를 전달 받아 로그인 처리")
    public ResponseEntity userLogin() throws NotImplementedException;
}
