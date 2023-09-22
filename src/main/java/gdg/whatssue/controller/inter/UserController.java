package gdg.whatssue.controller.inter;

import gdg.whatssue.service.dto.CheckIdDuplicateDto;
import gdg.whatssue.service.dto.UserLoginDto;
import gdg.whatssue.service.dto.UserSignUpDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping("/api/user")
public interface UserController {
    
    //회원가입
    @PostMapping("/sign-up")
    ResponseEntity userSignUp(UserSignUpDto userSignUpDto, BindingResult bindingResult) throws NotImplementedException;

    //아이디 중복 체크
    @PostMapping("/sign-up/check-id")
    ResponseEntity checkIdDuplicate(CheckIdDuplicateDto idDuplicateDto, BindingResult bindingResult) throws NotImplementedException;

    // 로그인
    @PostMapping("/login")
    ResponseEntity userLogin(UserLoginDto userLoginDto, BindingResult bindingResult) throws NotImplementedException;
}
