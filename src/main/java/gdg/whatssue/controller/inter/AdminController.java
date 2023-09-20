package gdg.whatssue.controller.inter;

import gdg.whatssue.service.dto.ClubDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/admin")
public interface AdminController {


    //모임 생성
    @PostMapping("/settings/club")
    ResponseEntity createClub(@RequestBody ClubDetailDto clubDetailDto) throws NotImplementedException;


    //모임 수정
    @PatchMapping("/settings/club")
    ResponseEntity updateClub(@RequestBody ClubDetailDto clubDetailDto) throws NotImplementedException;





}
