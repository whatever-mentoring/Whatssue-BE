package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.MemberController;
import gdg.whatssue.service.MemberService;
import gdg.whatssue.service.dto.ClubJoinRequestListDto;
import gdg.whatssue.service.dto.ClubMemberListDto;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequiredArgsConstructor
@Tag(name = "Member", description = "멤버 탈퇴, 가입요청 승인/거절, 가입요청 조회")
public class MemberControllerImpl implements MemberController {

    private final MemberService memberService;

    //멤버 탈퇴 처리
    @Override
    @Operation(
            summary = "멤버 탈퇴 api",
            description = "멤버 탈퇴 처리")
    public ResponseEntity deleteMember(Long memberId) {
        boolean result = memberService.deleteMember(memberId);
        
        if(!result){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("모임에 가입되어 있지 않은 회원입니다");
        }

        return ResponseEntity.status(HttpStatus.OK).body("탈퇴 처리 완료");
    }

    @Override
    @Operation(
            summary = "멤버 클럽 가입 승인 api",
            description = "멤버 클럽 가입 승인 처리")
    public ResponseEntity acceptJoinRequest(Long joinId) {
        
        boolean result = memberService.acceptJoinRequest(joinId);

        if(!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 가입 요청입니다");
        }

        return ResponseEntity.status(HttpStatus.OK).body("가입요청 승인 완료");
    }

    @Override
    @Operation(
            summary = "멤버 클럽 조인 거절 api",
            description = "멤버 클럽 조인 거절 처리")
    public ResponseEntity refuseJoinRequest(Long joinId) {

        boolean result = memberService.refuseJoinRequest(joinId);

        if(!result) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("존재하지 않는 가입 요청입니다");
        }

        return ResponseEntity.status(HttpStatus.OK).body("가입요청 거절 완료");
    }

    @Override
    @Operation(
            summary = "멤버 클럽 가입 요청 조회 api",
            description = "멤버 클럽 가입 요청 조회")
    public ResponseEntity getJoinRequestList() {
        List<ClubJoinRequestListDto> joinRequestList = memberService.getJoinRequestList();
        return ResponseEntity.status(HttpStatus.OK).body(joinRequestList);
    }

    @Override
    @Operation(
            summary = "멤버 리스트 조회 api",
            description = "멤버 리스트 조회")
    public ResponseEntity getAllMemberList() {
        List<ClubMemberListDto> allMemberList = memberService.getAllMemberList();

        return ResponseEntity.status(HttpStatus.OK).body(allMemberList);
    }

    @Override
    @Operation(
            summary = "멤버 가입 신청 api",
            description = "멤버 가입 신청, 가입 신청 창을 통해 받은 clubId 정보를 통해 가입 신청")
    public ResponseEntity requestJoin(Long clubId) {
        Long userId = 1L;
        return memberService.requestJoin(userId, clubId);
    }

    @Override
    @Operation(
            summary = "멤버 가입 신청 창 api",
            description = "멤버 가입 신청 창")
    public ResponseEntity requestJoinInfo(String teamId) {
        Long userId = 1L;
        return memberService.requestJoinInfo(teamId, userId);
    }

}
