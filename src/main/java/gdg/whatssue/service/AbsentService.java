package gdg.whatssue.service;

import gdg.whatssue.entity.ApplyOfficialAbsent;
import gdg.whatssue.entity.Schedule;
import gdg.whatssue.mapper.AbsentRequestMapper;
import gdg.whatssue.repository.ApplyOfficialAbsentRepository;
import gdg.whatssue.repository.MemberRepository;
import gdg.whatssue.repository.ScheduleRepository;
import gdg.whatssue.service.dto.AbsentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AbsentService {

    private final ApplyOfficialAbsentRepository applyOfficialAbsentRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    public ResponseEntity getAbsentRequest() {
        //어떤 클럽의 공결 list를 찾을 것인지 모르니 일단 임시로 1번 user가 속한 클럽의 스케줄을 조회
        Long clubId = 1L;
        //clubID 로 schedule 조회
        List<Schedule> scheduleList = scheduleRepository.findAllByClubId(clubId);
        //schedule List가 존재하지 않을 경우
        if(scheduleList == null){
            return ResponseEntity.badRequest().build();
        }

        //scheduleList 에서 공결 applyOfficalAbsentList조회
        List<ApplyOfficialAbsent> applyOfficialAbsentList = scheduleList.stream()
                .map(s -> s.getApplyOfficialAbsent())
                .collect(Collectors.toList());
        //만약 존재하지 않을 경우
        if(applyOfficialAbsentList == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(applyOfficialAbsentList);


    }

    public ResponseEntity acceptAbsentRequest(Long absentId) {
        //공결 승인
        ApplyOfficialAbsent applyOfficialAbsent = applyOfficialAbsentRepository.findById(absentId).orElse(null);
        if(applyOfficialAbsent == null){
            return ResponseEntity.badRequest().build();
        }
        applyOfficialAbsent.setAbsentIsAccepted(true);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity refuseAbsentRequest(Long absentId) {
        //공결 거절
        ApplyOfficialAbsent applyOfficialAbsent = applyOfficialAbsentRepository.findById(absentId).orElse(null);
        if(applyOfficialAbsent == null){
            return ResponseEntity.badRequest().build();
        }
        applyOfficialAbsent.setAbsentIsAccepted(false);
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
