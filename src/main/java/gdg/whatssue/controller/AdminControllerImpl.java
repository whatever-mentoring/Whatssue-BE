package gdg.whatssue.controller;

import gdg.whatssue.controller.inter.AdminController;
import gdg.whatssue.service.AdminService;
import gdg.whatssue.service.dto.ClubDetailDto;
import gdg.whatssue.service.dto.LinkInfoDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {

    private final AdminService adminService;


    @Override
    public ResponseEntity createClub(ClubDetailDto clubDetailDto) throws NotImplementedException {
        return adminService.createClub(clubDetailDto);
    }

    @Override
    @Transactional
    public ResponseEntity updateClub(ClubDetailDto clubDetailDto)throws NotImplementedException{
        return adminService.updateClub(clubDetailDto);
    }

    @Override
    public ResponseEntity createInviteLink(LinkInfoDto linkInfoDto) throws NotImplementedException {
        Long userId = 1L;
        return adminService.createInviteLink(userId,linkInfoDto);

    }


}
