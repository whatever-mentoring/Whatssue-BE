package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.MemberController;
import gdg.whatssue.service.MemberService;
import gdg.whatssue.service.dto.ClubJoinRequestListDto;
import gdg.whatssue.service.dto.ClubMemberListDto;
import java.util.List;

import jdk.jshell.spi.ExecutionControl;
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
        boolean result = memberService.deleteMember(memberId);
        
        if(!result){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("모임에 가입되어 있지 않은 회원입니다");
        }

        return ResponseEntity.status(HttpStatus.OK).body("탈퇴 처리 완료");
    }

    @Override
    public ResponseEntity acceptJoinRequest(Long joinId) {
        
        boolean result = memberService.acceptJoinRequest(joinId);

        if(!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 가입 요청입니다");
        }

        return ResponseEntity.status(HttpStatus.OK).body("가입요청 승인 완료");
    }

    @Override
    public ResponseEntity refuseJoinRequest(Long joinId) {

        boolean result = memberService.refuseJoinRequest(joinId);

        if(!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 가입 요청입니다");
        }

        return ResponseEntity.status(HttpStatus.OK).body("가입요청 거절 완료");
    }

    @Override
    public ResponseEntity getJoinRequestList() {
        List<ClubJoinRequestListDto> joinRequestList = memberService.getJoinRequestList();
        return ResponseEntity.status(HttpStatus.OK).body(joinRequestList);
    }

    @Override
    public ResponseEntity getAllMemberList() {
        List<ClubMemberListDto> allMemberList = memberService.getAllMemberList();

        return ResponseEntity.status(HttpStatus.OK).body(allMemberList);
    }

    @Override
    public ResponseEntity requestJoin(String teamId) throws ExecutionControl.NotImplementedException {
        Long userId = 1L;
        return memberService.requestJoin(userId, teamId);
    }
}
