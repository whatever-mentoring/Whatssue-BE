package gdg.whatssue.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserLoginDto {

    @NotBlank
    private String memberNickName;

    @NotBlank
    private String memberPw;
}
