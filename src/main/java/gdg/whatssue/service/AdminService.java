package gdg.whatssue.service;

import gdg.whatssue.entity.Admin;
import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.Member;
import gdg.whatssue.repository.AdminRepository;
import gdg.whatssue.repository.ClubRepository;
import gdg.whatssue.repository.MemberRepository;
import gdg.whatssue.service.dto.ClubDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final AdminRepository adminRepository;

    public ResponseEntity createClub(ClubDetailDto clubDetailDto) {
        Long userId = 2L;
        Optional<Club> club = adminRepository.findById(userId).map(Admin::getClub);
        //이미 클럽이 존재하는경우 예외처리
        if (club.isPresent()) {
            return ResponseEntity.badRequest().body("이미 클럽이 존재합니다.");
        }
        //존재하지 않는 경우
        else{
            Club newclub = Club.builder()
                    .clubName(clubDetailDto.getClubName())
                    .clubInfo(clubDetailDto.getClubInfo())
                    .clubCategory(clubDetailDto.getClubCategory())
                    .build();
            clubRepository.save(newclub);

            Member member = memberRepository.findById(userId).get();
            //Admin 테이블에 user_id를 넣어줘야함
            Admin admin = Admin.builder()
                    .club(newclub)
                    .member(member)
                    .role("owner")
                    .build();
            adminRepository.save(admin);
            return ResponseEntity.ok().build();
        }


    }

    public ResponseEntity updateClub(ClubDetailDto clubDetailDto) {
        Long userId = 1L;
        Club club = memberRepository.findById(userId).get().getClub();
        //클럽이 존재하지 않는경우 예외처리
        if (club == null) {
            return ResponseEntity.badRequest().body("클럽이 존재하지 않습니다.");
        }
        club.updateClub(clubDetailDto.getClubName(), clubDetailDto.getClubInfo(), clubDetailDto.getClubCategory());
        //정상 작동시 200반환
        return ResponseEntity.ok().body("모임 정보가 수정되었습니다.");

    }
}
