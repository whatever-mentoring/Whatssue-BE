package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.AdminController;
import gdg.whatssue.service.AdminService;
import gdg.whatssue.service.dto.ClubDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import gdg.whatssue.service.dto.LinkInfoDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Admin", description = "admin 의 모임생성/수정 관련 api")
public class AdminControllerImpl implements AdminController {

    private final AdminService adminService;


    @Override
    @Operation(
            summary = "멤버 - 모임 생성 api",
            description = "Request Body로 모임 정보를 json 형태로 입력 받아 모임을 생성")
    public ResponseEntity createClub(ClubDetailDto clubDetailDto) throws NotImplementedException {
        return adminService.createClub(clubDetailDto);
    }

    @Override
    @Transactional
    @Operation(
            summary = "관리자 - 모임 수정 api",
            description = "Request Body로 모임 정보를 json 형태로 입력 받아 모임 정보를 수정")
    public ResponseEntity updateClub(ClubDetailDto clubDetailDto)throws NotImplementedException{
        return adminService.updateClub(clubDetailDto);
    }

    @Override
    public ResponseEntity createInviteLink(LinkInfoDto linkInfoDto) throws NotImplementedException {
        Long userId = 1L;
        return adminService.createInviteLink(userId,linkInfoDto);

    }

    @Override
    public ResponseEntity deleteInviteLink(Long linkId) throws NotImplementedException {
        Long userId = 1L;
        return adminService.deleteInviteLink(userId,linkId);
    }

    @Override
    public ResponseEntity getInviteLink() throws NotImplementedException {
        Long userId = 1L;
        return adminService.getInviteLink(userId);
    }


}
