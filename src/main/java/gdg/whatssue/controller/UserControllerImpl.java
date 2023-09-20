package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.UserController;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(
            summary = "회원가입 api",
            description = "회원가입")
    public ResponseEntity userSignUp() throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    @Override
    @PostMapping("/checkId")
    @Operation(
            summary = "아이디 중복 체크 api",
            description = "아이디 중복 체크")
    public ResponseEntity checkIdDuplicate() throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    @Override
    @PostMapping("/login")
    @Operation(
            summary = "로그인 api",
            description = "로그인")
    public ResponseEntity userLogin() throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }


}
