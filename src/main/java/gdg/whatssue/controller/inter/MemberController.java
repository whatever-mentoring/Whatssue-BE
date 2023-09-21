package gdg.whatssue.controller.inter;

import io.swagger.v3.oas.annotations.Operation;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/member")
public interface MemberController {
    //멤버 탈퇴처리
    @DeleteMapping("/delete/{memberId}")
    ResponseEntity deleteMember(@PathVariable Long memberId) throws NotImplementedException;

    //멤버 가입요청 승인
    @PostMapping("/join-accept/{joinId}")
    ResponseEntity acceptJoinRequest(@PathVariable Long joinId) throws NotImplementedException;

    //멤버 가입요청 거절
    @PostMapping("/join-refuse/{joinId}")
    ResponseEntity refuseJoinRequest(@PathVariable Long joinId) throws NotImplementedException;

    //멤버 가입요청 리스트 조회
    @GetMapping("/join-list")
    ResponseEntity getJoinRequestList(Authentication auth) throws NotImplementedException;

    //멤버 목록 조회
    @GetMapping("/manage")
    ResponseEntity getAllMemberList() throws NotImplementedException;

    //멤버 가입 신청
    @PostMapping("/join/request")
    ResponseEntity requestJoin(@RequestParam("teamId") String teamId) throws NotImplementedException;

    //멤버 가입 신청 창
    @GetMapping("/join/request")
    ResponseEntity requestJoinInfo(@RequestParam("teamId") String teamId) throws NotImplementedException;

}
