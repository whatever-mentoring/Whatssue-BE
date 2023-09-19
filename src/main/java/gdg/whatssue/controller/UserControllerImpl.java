package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.UserController;
import gdg.whatssue.service.UserService;
import gdg.whatssue.service.dto.UserSignUpDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
@Tag(name = "User", description = "회원가입, 로그인, 아이디 중복 체크")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity userSignUp(@Valid @RequestBody UserSignUpDto userSignUpDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 입력 값입니다");
        }

        userService.userSignUp(userSignUpDto);
        return ResponseEntity.status(HttpStatus.OK).body("가입되었습니다");
    }

    @Override
    public ResponseEntity checkIdDuplicate() throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }

    @Override
    public ResponseEntity userLogin() throws NotImplementedException {
        //"TODO: This api should be implemented"
        throw new NotImplementedException("hi");
    }


}
