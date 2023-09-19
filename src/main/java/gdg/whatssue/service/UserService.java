package gdg.whatssue.service;

import gdg.whatssue.repository.MemberRepository;
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
}
