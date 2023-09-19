package gdg.whatssue.controller.inter;

import gdg.whatssue.service.dto.ClubDetailDto;
import gdg.whatssue.service.dto.LinkInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/admin")
public interface AdminController {


    //모임 생성
    @PostMapping("/settings/club")
    @Operation(tags = { "모임 생성" },
            summary = "멤버 - 모임 생성 api",
            description = "Request Body로 모임 정보를 json 형태로 입력 받아 모임을 생성")
    ResponseEntity createClub(@RequestBody ClubDetailDto clubDetailDto) throws NotImplementedException;


    //모임 수정
    @PatchMapping("/settings/club")
    @Operation(tags = { "모임 수정" },
            summary = "관리자 - 모임 수정 api",
            description = "Request Body로 모임 정보를 json 형태로 입력 받아 모임 정보를 수정")
    ResponseEntity updateClub(@RequestBody ClubDetailDto clubDetailDto) throws NotImplementedException;


    //초대 링크 생성
    @PostMapping("/link")
    @Operation(tags = { "초대 링크 생성" },
            summary = "관리자 - 초대 링크 생성 api",
            description = "링크 이름을 입력받고, 모임 id를 토큰 헤더를 통해 입력 받아 해당 모임의 초대 링크를 생성")
    ResponseEntity createInviteLink(LinkInfoDto linkInfoDto) throws NotImplementedException;
}
