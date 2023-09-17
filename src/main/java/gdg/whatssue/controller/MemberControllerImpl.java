package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.MemberController;
import gdg.whatssue.service.MemberService;
import gdg.whatssue.service.dto.ClubJoinRequestListDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberControllerImpl implements MemberController {

    private final MemberService memberService;

    //멤버 탈퇴 처리
    @Override
    public ResponseEntity deleteMember(Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @Override
    public ResponseEntity acceptJoinRequest(Long joinId) {
        memberService.acceptJoinRequest(joinId);
        return null;
    }

    @Override
    public ResponseEntity refuseJoinRequest(Long memberId) {
        return null;
    }

    @Override
    public ResponseEntity getJoinRequestList() {
        List<ClubJoinRequestListDto> joinRequestList = memberService.getJoinRequestList();
        return ResponseEntity.status(HttpStatus.OK).body(joinRequestList);
    }

    @Override
    public ResponseEntity getAllMemberList() {
        return null;
    }
}
