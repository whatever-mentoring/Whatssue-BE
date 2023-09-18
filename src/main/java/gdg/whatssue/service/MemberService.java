package gdg.whatssue.service;

import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.ClubJoinRequest;
import gdg.whatssue.entity.ClubMemberMapping;
import gdg.whatssue.entity.Member;
import gdg.whatssue.repository.ClubJoinRequestRepository;
import gdg.whatssue.repository.ClubMemberMappingRepository;
import gdg.whatssue.repository.ClubRepository;
import gdg.whatssue.repository.MemberRepository;
import gdg.whatssue.service.dto.ClubJoinRequestListDto;
import gdg.whatssue.service.dto.ClubMemberListDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

        clubMemberMappingRepository.delete(memberMapping);
        return true;
    }

    @Transactional
    public boolean acceptJoinRequest(Long joinId){
        ClubJoinRequest clubJoinRequest = joinRequestRepository.findById(joinId).orElse(null);

        if(clubJoinRequest == null) {
            return false;
        }

        clubMemberMappingRepository.save(
            ClubMemberMapping.builder()
            .member(clubJoinRequest.getMember())
            .club(clubJoinRequest.getClub()).build());

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
                .checkedCount(m.getMember().getCheckedListByUser().getCheckedCount())
                .absentCount(m.getMember().getCheckedListByUser().getAbsentCount())
                .officialAbsentCount(m.getMember().getCheckedListByUser().getOfficialAbsentCount())
                .build())
            .collect(Collectors.toList());

        return allMemberList;
    }

}
