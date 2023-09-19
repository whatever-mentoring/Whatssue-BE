package gdg.whatssue.controller.inter;

import io.swagger.v3.oas.annotations.Operation;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/member")
public interface MemberController {

    //멤버 탈퇴처리
    @DeleteMapping("/delete/{memberId}")
    @Operation(tags = { "멤버 탈퇴처리" },
            summary = "멤버 - 탈퇴처리 api",
            description = "멤버 id를 통해 해당 멤버를 탈퇴처리")
    ResponseEntity deleteMember(@PathVariable Long memberId) throws NotImplementedException;

    //멤버 가입요청 승인
    @PostMapping("/join-accept/{joinId}")
    @Operation(tags = { "멤버 가입요청 승인" },
            summary = "멤버 - 가입요청 승인 api",
            description = "가입요청 id를 통해 해당 가입요청을 승인")
    ResponseEntity acceptJoinRequest(@PathVariable Long joinId) throws NotImplementedException;

    //멤버 가입요청 거절
    @PostMapping("/join-refuse/{joinId}")
    @Operation(tags = { "멤버 가입요청 거절" },
            summary = "멤버 - 가입요청 거절 api",
            description = "가입요청 id를 통해 해당 가입요청을 거절")
    ResponseEntity refuseJoinRequest(@PathVariable Long joinId) throws NotImplementedException;

    //멤버 가입요청 리스트 조회
    @GetMapping("/join-list")
    @Operation(tags = { "멤버 가입요청 리스트 조회" },
            summary = "멤버 - 가입요청 리스트 조회 api",
            description = "팀 id를 토큰 헤더를 통해 입력 받고, 해당 팀의 가입요청 리스트를 조회")
    ResponseEntity getJoinRequestList() throws NotImplementedException;

    //멤버 목록 조회
    @GetMapping("/manage")
    @Operation(tags = { "멤버 목록 조회" },
            summary = "멤버 - 목록 조회 api",
            description = "팀 id를 토큰 헤더를 통해 입력 받고, 해당 팀의 멤버 목록을 조회")
    ResponseEntity getAllMemberList() throws NotImplementedException;

    //멤버 가입 신청
    @PostMapping("/join/request")
    @Operation(tags = { "멤버 가입 신청" },
            summary = "멤버 - 가입 신청 api",
            description = "팀 id를 토큰 헤더를 통해 입력 받고, query parma 을 통해 link string 입력받아 해당 팀에 가입 신청")
    ResponseEntity requestJoin(@RequestParam("teamId") String teamId) throws NotImplementedException;

    //멤버 가입 신청 창
    @GetMapping("/join/request")
    @Operation(tags = { "멤버 가입 신청 창" },
            summary = "멤버 - 가입 신청 창 api",
            description = "팀 id를 토큰 헤더를 통해 입력 받고, query parma 을 통해 link string 입력받아  해당 팀에 가입 신청 창을 띄움")
    ResponseEntity requestJoinInfo(@RequestParam("teamId") String teamId) throws NotImplementedException;

}
