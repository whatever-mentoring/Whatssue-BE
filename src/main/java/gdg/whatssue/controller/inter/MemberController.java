package gdg.whatssue.controller.inter;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    ResponseEntity getJoinRequestList() throws NotImplementedException;

    //멤버 목록 조회
    @GetMapping("/manage")
    ResponseEntity getAllMemberList() throws NotImplementedException;
}
