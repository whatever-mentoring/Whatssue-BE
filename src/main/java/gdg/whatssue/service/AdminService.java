package gdg.whatssue.service;

import gdg.whatssue.entity.Admin;
import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.Link;
import gdg.whatssue.entity.Member;
import gdg.whatssue.repository.AdminRepository;
import gdg.whatssue.repository.ClubRepository;
import gdg.whatssue.repository.LinkRepository;
import gdg.whatssue.repository.MemberRepository;
import gdg.whatssue.service.dto.ClubDetailDto;
import gdg.whatssue.service.dto.LinkDetailDto;
import gdg.whatssue.service.dto.LinkInfoDto;
import gdg.whatssue.service.dto.LinkResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final AdminRepository adminRepository;
    private final LinkRepository linkRepository;

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

    public ResponseEntity createInviteLink(Long userId, LinkInfoDto linkInfoDto){
        Club club = memberRepository.findById(userId).get().getClub();
        //클럽이 존재하지 않는경우 예외처리
        if (club == null) {
            return ResponseEntity.badRequest().body("클럽이 존재하지 않습니다.");
        }
        else{
            //랜덤한 string 값 생성 함수 호출
            String str = createLink();
            String link = "member/join/request?teamId="+str;
            //Link Entity 에 Builder 이용 저장
            linkRepository.save(Link.builder()
                    .linkName(linkInfoDto.getLinkName())
                    .linkUrl(str)
                    .club(club)
                    .build());

            //save 후 생긴 linkId get
            Long linkId = linkRepository.findByLinkUrl(link).get().getLinkId();
            //결과
            LinkResultDto linkResultDto = LinkResultDto.builder()
                    .LinkId(linkId.toString())
                    .LinkUrl(link)
                    .LinkName(linkInfoDto.getLinkName())
                    .build();

            return ResponseEntity.ok().body(linkResultDto);
        }

    }
    public static String createLink(){
        //랜덤한 string 값 생성
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public ResponseEntity deleteInviteLink(Long userId, Long linkId) {
        //초대 링크 삭제
        Club club = memberRepository.findById(userId).get().getClub();
        //클럽이 존재하지 않는경우 예외처리
        if (club == null) {
            return ResponseEntity.badRequest().body("클럽이 존재하지 않습니다.");
        }else {
            try{
                Link link = linkRepository.findByLinkId(linkId);
                if(link!=null){
                    //club 의 link null 로 변경
                    club.changeLink(null);
                    //link 테이블에서 삭제
                    linkRepository.delete(link);
                    return ResponseEntity.ok().body("초대 링크가 삭제되었습니다.");
                }else{
                    return ResponseEntity.badRequest().body("존재하지 않는 링크입니다.");
                }

            }catch (EmptyResultDataAccessException e){
                return ResponseEntity.badRequest().body("존재하지 않는 링크입니다.");
            }
        }

    }

    public ResponseEntity getInviteLink(Long userId) {
        //초대 링크 조회
        Club club = memberRepository.findById(userId).get().getClub();
        //클럽이 존재하지 않는경우 예외처리
        if (club == null) {
            return ResponseEntity.badRequest().body("클럽이 존재하지 않습니다.");
        }
        else{
            //링크테이블 리스트로 조회
            List<Link> linkList = linkRepository.findAllByClub(club);
            //linkList 를 LinkDetailDto 로 stream 사용하여 변환
            List<LinkDetailDto> linkDetailDtoList = linkList.stream().map(link -> LinkDetailDto.builder()
                    .linkId(link.getLinkId().toString())
                    .linkName(link.getLinkName())
                    .linkUrl(link.getLinkUrl())
                    .clubId(link.getClub().getClubId().toString())
                    .clubName(link.getClub().getClubName())
                    .clubInfo(link.getClub().getClubInfo())
                    .clubCategory(link.getClub().getClubCategory())
                    .build()).toList();

            return ResponseEntity.ok().body(linkDetailDtoList);

        }
    }
}
