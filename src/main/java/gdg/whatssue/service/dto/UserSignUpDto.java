package gdg.whatssue.service.dto;

import gdg.whatssue.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignUpDto {

    @NotBlank
    private String memberName;

    @NotBlank
    private String memberNickName;

    @NotBlank
    private String memberPw;

    @Email
    @NotBlank
    private String memberEmail;

    @NotBlank
    private String memberPhone;

    public Member toEntity() {
        return Member.builder()
            .memberName(memberName)
            .memberNickName(memberNickName)
            .memberPw(memberPw)
            .memberEmail(memberEmail)
            .memberPhone(memberPhone).build();
    }
}