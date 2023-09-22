package gdg.whatssue.service;

import gdg.whatssue.entity.Member;
import gdg.whatssue.repository.MemberRepository;
import gdg.whatssue.service.dto.UserLoginDto;
import gdg.whatssue.service.dto.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MemberRepository memberRepository;

    public void userSignUp(UserSignUpDto userSignUpDto){
        memberRepository.save(userSignUpDto.toEntity());
    }

    public Member getLoginUserByLoginId(String loginId){
        return memberRepository.findByMemberNickName(loginId).get();
    }

    public boolean checkIdDuplicate(String checkId) {
        return memberRepository.findAll().stream().anyMatch(m -> m.getMemberNickName().equals(checkId));
    }

    public String userLogin(UserLoginDto userLoginDto) {
        Member member = memberRepository.findByMemberNickName(userLoginDto.getMemberNickName())
            .filter(m -> m.getMemberPw().equals(userLoginDto.getMemberPw()))
            .orElse(null);

        if(member == null) {
            return null;
        }

        return member.getMemberNickName();
    }
}
