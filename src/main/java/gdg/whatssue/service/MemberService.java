package gdg.whatssue.service;

import gdg.whatssue.entity.*;
import gdg.whatssue.entity.CheckedListByUser;
import gdg.whatssue.mapper.ClubDetailMapper;
import gdg.whatssue.repository.*;
import gdg.whatssue.service.dto.ClubDetailDto;
import gdg.whatssue.service.dto.ClubJoinRequestListDto;
import gdg.whatssue.service.dto.ClubMemberListDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    //클럽아이디 1이라고 가정
    static final Long CLUB_ID = 1L;

    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final ClubJoinRequestRepository joinRequestRepository;
    private final ClubMemberMappingRepository clubMemberMappingRepository;
    private final LinkRepository linkRepository;

    private final CheckedListByUserRepository checkedListByUserRepository;

    @Transactional
    public boolean deleteMember(Long memberId){
        
        Club club = clubRepository.findById(CLUB_ID).get();

        //삭제 후 결과 반환. 삭제 성공 시 true, 삭제 값 없을 시 false
        List<ClubMemberMapping> memberList = club.getMemberListV2();
        ClubMemberMapping memberMapping = memberList.stream()
            .filter(m -> m.getMember().getMemberId().equals(memberId))
            .findFirst().orElse(null);

        if(memberMapping == null){
            return false;
        }

        checkedListByUserRepository.delete(memberMapping.getCheckedListByUser());
        clubMemberMappingRepository.delete(memberMapping);
        return true;
    }

    @Transactional
    public boolean acceptJoinRequest(Long joinId){
        ClubJoinRequest clubJoinRequest = joinRequestRepository.findById(joinId).orElse(null);

        if(clubJoinRequest == null) {
            return false;
        }

        ClubMemberMapping clubMemberMapping = ClubMemberMapping.builder()
            .member(clubJoinRequest.getMember())
            .club(clubJoinRequest.getClub()).build();

        clubMemberMappingRepository.save(clubMemberMapping);

        checkedListByUserRepository.save(
            CheckedListByUser.builder()
                .club_member_mapping(clubMemberMapping)
                .checkedCount(0)
                .absentCount(0)
                .officialAbsentCount(0).build());

        joinRequestRepository.delete(clubJoinRequest);
        return true;
    }

    @Transactional
    public boolean refuseJoinRequest(Long joinId){
        ClubJoinRequest clubJoinRequest = joinRequestRepository.findById(joinId).orElse(null);

        if(clubJoinRequest == null) {
            return false;
        }

        joinRequestRepository.delete(clubJoinRequest);
        return true;
    }

    public List<ClubJoinRequestListDto> getJoinRequestList(){
        List<ClubJoinRequest> requestList = joinRequestRepository.findAll();

        List<ClubJoinRequestListDto> clubJoinRequestList = requestList.stream()
            .filter(r -> r.getClub().getClubId().equals(CLUB_ID))
            .map(r -> ClubJoinRequestListDto.builder()
                .clubJoinRequestId(r.getClubJoinRequestId())
                .userName(r.getMember().getMemberName())
                .requestDate(r.getCreatedAt().toString().substring(0,10)).build())
            .collect(Collectors.toList());

        return clubJoinRequestList;
    }

    public List<ClubMemberListDto> getAllMemberList(){

        List<ClubMemberMapping> memberListV2 = clubRepository.findById(CLUB_ID).get().getMemberListV2();

        List<ClubMemberListDto> allMemberList = memberListV2.stream()
            .map(m -> ClubMemberListDto.builder()
                .memberId(m.getMember().getMemberId())
                .memberName(m.getMember().getMemberName())
                .checkedCount(m.getCheckedListByUser().getCheckedCount())
                .absentCount(m.getCheckedListByUser().getAbsentCount())
                .officialAbsentCount(m.getCheckedListByUser().getOfficialAbsentCount())
                .build())
            .collect(Collectors.toList());

        return allMemberList;
    }
    //가입 요청
    public ResponseEntity requestJoin(Long userId, Long clubId) {
        //clubId 로 Club 정보 가져오기
        Club club = clubRepository.findById(clubId).get();
        Member member = memberRepository.findById(userId).orElse(null);
        if(member == null){
                //존재하지 않는 회원입니다.문구 를 리턴
                return ResponseEntity.badRequest().body("회원이 존재하지 않습니다.");
        }
        else{
                ClubJoinRequest clubJoinRequest = ClubJoinRequest.builder()
                    .club(club)
                    .member(member)
                    .build();
                joinRequestRepository.save(clubJoinRequest);
                return ResponseEntity.ok().body("가입 요청이 완료되었습니다.");
        }
    }

    public ResponseEntity requestJoinInfo(String teamId, Long userId) {
        //Link 테이블의 LinkUrl로 club 찾기
        Link link = linkRepository.findByLinkUrl(teamId).orElse(null);
        if(link == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else{
            //clubDto 리턴
            Club club = link.getClub();
            //ClubDetailMapper 로 Dto 로 변환
            ClubDetailDto clubDetailDto = ClubDetailMapper.INSTANCE.toDTO(club);
            return ResponseEntity.ok().body(clubDetailDto);
        }

    }
}
