package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.UserController;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user/sign-up")
@Tag(name = "User", description = "회원가입, 로그인, 아이디 중복 체크")
public class UserControllerImpl implements UserController {

    @Override
    @PostMapping("")
    public ResponseEntity userSignUp() throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    @Override
    @PostMapping("/checkId")
    public ResponseEntity checkIdDuplicate() throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity userLogin() throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }


}
