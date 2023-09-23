package gdg.whatssue.service;

import gdg.whatssue.entity.ApplyOfficialAbsent;
import gdg.whatssue.entity.AttendanceByUserBySchedule;
import gdg.whatssue.entity.Member;
import gdg.whatssue.entity.Schedule;
import gdg.whatssue.mapper.AbsentRequestMapper;
import gdg.whatssue.repository.ApplyOfficialAbsentRepository;
import gdg.whatssue.repository.AttendanceByUserByScheduleRepository;
import gdg.whatssue.repository.MemberRepository;
import gdg.whatssue.repository.ScheduleRepository;
import gdg.whatssue.service.dto.AbsentListDto;
import gdg.whatssue.service.dto.AbsentRequestDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AbsentService {

    private final ApplyOfficialAbsentRepository applyOfficialAbsentRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;
    private final AttendanceByUserByScheduleRepository attendanceByUserByScheduleRepository;


    //공결 테이블 전체 조회
    public ResponseEntity getAbsentRequest() {
        //어떤 클럽의 공결 list를 찾을 것인지 모르니 일단 임시로 1번 user가 속한 클럽의 스케줄을 조회
        Long clubId = 1L;
        //clubId로 스케줄 조회
        List<Schedule> scheduleList = scheduleRepository.findAllByClubId(clubId);
        //스케줄 id 에 해당하는 공결 조회


        // applyOfficialAbsentList에서 AbsentListDto로 변환
        List<AbsentListDto> absentListDtoList =  scheduleList.stream()
                .map(s -> {
                    ApplyOfficialAbsent a = s.getApplyOfficialAbsent();
                    if (a != null && a.getMember() != null) {
                        return AbsentListDto.builder()
                                .applyOfficialAbsentId(String.valueOf(a.getApplyOfficialAbsentId()))
                                .scheduleId(String.valueOf(s.getScheduleId()))
                                .scheduleTitle(s.getScheduleTitle())
                                .absentReason(a.getAbsentReason())
                                .absentDate(String.valueOf(a.getAbsentDate()))
                                .absentIsAccepted(a.getAbsentIsAccepted())
                                .memberId(String.valueOf(a.getMember().getMemberId()))
                                .memberName(a.getMember().getMemberName())
                                .memberNickName(a.getMember().getMemberNickName())
                                .memberEmail(a.getMember().getMemberEmail())
                                .memberPhone(a.getMember().getMemberPhone())
                                .build();
                    }
                    return null; // null인 객체는 건너뛰기
                })
                .filter(Objects::nonNull) // null인 객체 필터링
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

        //Member와 Schedule 을 applyOfficialAbsent에서 가져옴
        Member member = applyOfficialAbsent.getMember();
        Schedule schedule = applyOfficialAbsent.getSchedule();
        //공결 승인시 출석 테이블의 출석 여부를 공결로 변경
        AttendanceByUserBySchedule attendanceByUserBySchedule = attendanceByUserByScheduleRepository.findByMemberAndSchedule(member, schedule);
        //예외처리
        if(attendanceByUserBySchedule == null){
            return ResponseEntity.ok().build();
        }
        else{
            attendanceByUserBySchedule.changeAttendance("공결");
        }

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
        //memberId 선언
        Long memberId = 1L;
        Member member = memberRepository.findById(memberId).orElse(null);

        //공결 테이블에 추가하여 신청
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);
        if(schedule == null){
            return ResponseEntity.badRequest().build();
        }

        //Dto -> Entity 위해 AbsentRequestMapper 사용
        //ApplyOfficialAbsent applyOfficialAbsent = AbsentRequestMapper.INSTANCE.toEntity(absentRequestDto);

        try{

            //Dto -> Entity builder로 변환
            ApplyOfficialAbsent applyOfficialAbsent1 = ApplyOfficialAbsent.builder()
                    .absentReason(absentRequestDto.getAbsentReason())
                    .absentDate(LocalDate.parse(absentRequestDto.getAbsentDate()))
                    .build();
            //ApplyOfficalAbsent 에 Schedule 객체 전달
            applyOfficialAbsent1.saveSchedule(schedule);
            applyOfficialAbsent1.saveMember(member);
            String absentIsAccepted = "WAIT";
            applyOfficialAbsent1.saveIsAccepted(absentIsAccepted);
            applyOfficialAbsentRepository.save(applyOfficialAbsent1);

            //ReponseEntity key:value 로 공결 id 리턴
            return ResponseEntity.ok().body("absentId :"+applyOfficialAbsent1.getApplyOfficialAbsentId());

        }catch (Exception e){
            e.printStackTrace();;
            return ResponseEntity.badRequest().build();
        }

    }
}
