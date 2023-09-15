package gdg.whatssue.service;

import gdg.whatssue.entity.ApplyOfficialAbsent;
import gdg.whatssue.entity.Schedule;
import gdg.whatssue.mapper.AbsentRequestMapper;
import gdg.whatssue.repository.ApplyOfficialAbsentRepository;
import gdg.whatssue.repository.MemberRepository;
import gdg.whatssue.repository.ScheduleRepository;
import gdg.whatssue.service.dto.AbsentListDto;
import gdg.whatssue.service.dto.AbsentRequestDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AbsentService {

    private final ApplyOfficialAbsentRepository applyOfficialAbsentRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    //공결 테이블 전체 조회
    public ResponseEntity getAbsentRequest() {
        //어떤 클럽의 공결 list를 찾을 것인지 모르니 일단 임시로 1번 user가 속한 클럽의 스케줄을 조회
        Long clubId = 1L;
        //clubID 로 schedule 조회
        List<Schedule> scheduleList = scheduleRepository.findAllByClubId(clubId);

        List<ApplyOfficialAbsent> applyOfficialAbsentList = scheduleList.stream()
                .map(s -> s.getApplyOfficialAbsent())
                .collect(Collectors.toList());

        if (applyOfficialAbsentList == null) {
            return ResponseEntity.badRequest().build();
        }

        //applyOfficalAbsentList 를 AbsentListDto 로 변환
        List<AbsentListDto> absentListDtoList = applyOfficialAbsentList.stream()
                .filter(a -> a != null) // null인 객체 건너뛰기
                .map(a -> AbsentListDto.builder()
                        .applyOfficialAbsentId(String.valueOf(a.getApplyOfficialAbsentId()))
                        .absentReason(a.getAbsentReason())
                        .absentIsAccepted(String.valueOf(a.getAbsentIsAccepted()))
                        .absentDate(a.getAbsentDate().toString())
                        .build())
                .collect(Collectors.toList());


        return ResponseEntity.ok(absentListDtoList);
    }



    public ResponseEntity acceptAbsentRequest(Long absentId) {
        //공결 승인
        ApplyOfficialAbsent applyOfficialAbsent = applyOfficialAbsentRepository.findById(absentId).orElse(null);
        if(applyOfficialAbsent == null){
            return ResponseEntity.badRequest().build();
        }
        String absentIsAccepted = "Approved";
        applyOfficialAbsent.AcceptAbsent(absentIsAccepted);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity refuseAbsentRequest(Long absentId) {
        //공결 거절
        ApplyOfficialAbsent applyOfficialAbsent = applyOfficialAbsentRepository.findById(absentId).orElse(null);
        if(applyOfficialAbsent == null){
            return ResponseEntity.badRequest().build();
        }
        String absentIsAccepted = "denied";
        applyOfficialAbsent.AcceptAbsent(absentIsAccepted);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity requestAbsent(Long scheduleId, AbsentRequestDto absentRequestDto) {
        //공결 테이블에 추가하여 신청
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);
        if(schedule == null){
            return ResponseEntity.badRequest().build();
        }
        //
        //Dto -> Entity 위해 AbsentRequestMapper 사용
        ApplyOfficialAbsent applyOfficialAbsent = AbsentRequestMapper.INSTANCE.toEntity(absentRequestDto);

        try{
            //ApplyOfficalAbsent 에 Schedule 객체 전달
            applyOfficialAbsent.saveSchedule(schedule);
            applyOfficialAbsentRepository.save(applyOfficialAbsent);

            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }
}
