package gdg.whatssue.service;

import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.ClubJoinRequest;
import gdg.whatssue.entity.Member;
import gdg.whatssue.repository.ClubJoinRequestRepository;
import gdg.whatssue.repository.ClubRepository;
import gdg.whatssue.repository.MemberRepository;
import gdg.whatssue.service.dto.ClubJoinRequestListDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final ClubRepository clubRepository;
    private final MemberRepository memberRepository;
    private final ClubJoinRequestRepository joinRequestRepository;

    @Transactional
    public boolean deleteMember(Long memberId){
        //클럽아이디 1L이라고 가정
        Club club = clubRepository.findById(1L).get();

        //삭제 후 결과 반환. 삭제 성공 시 true, 삭제 값 없을 시 false
        boolean result = club.getMemberList().remove(memberRepository.findById(memberId));

        clubRepository.save(club);

        return result;
    }

    public void acceptJoinRequest(Long joinId){
        ClubJoinRequest clubJoinRequest = joinRequestRepository.findById(joinId).orElse(null);

        if(clubJoinRequest == null) {
//            없습니다 반환
        }
        
        //해당 클럽에 해당 멤버 가입시키고 레포에서 삭제
    }

    public void refuseJoinRequest(Long joinId){
        ClubJoinRequest clubJoinRequest = joinRequestRepository.findById(joinId).orElse(null);

        if(clubJoinRequest == null) {
//            없습니다 반환
        }

        //거절이니 레포에서 삭제
    }

    public List<ClubJoinRequestListDto> getJoinRequestList(){
        Long clubId = 1L;
        List<ClubJoinRequest> requestList = joinRequestRepository.findAll();

        List<ClubJoinRequestListDto> clubJoinRequestList = requestList.stream()
            .filter(r -> r.getClub().getClubId().equals(clubId))
            .map(r -> ClubJoinRequestListDto.builder()
                .clubJoinRequestId(r.getClubJoinRequestId())
                .userName(r.getMember().getMemberName())
                .requestDate(r.getCreatedAt().toString().substring(0,10)).build())
            .collect(Collectors.toList());

        return clubJoinRequestList;
    }

    public void getAllMemberList(){

    }

}
