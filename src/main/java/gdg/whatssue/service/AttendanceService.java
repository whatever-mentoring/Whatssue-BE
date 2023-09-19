package gdg.whatssue.service;

import gdg.whatssue.entity.AttendanceByUserBySchedule;
import gdg.whatssue.entity.Schedule;
import gdg.whatssue.repository.ApplyOfficialAbsentRepository;
import gdg.whatssue.repository.AttendanceByUserByScheduleRepository;
import gdg.whatssue.repository.MemberRepository;
import gdg.whatssue.repository.ScheduleRepository;
import gdg.whatssue.service.dto.AttendanceStateBySheduleDto;
import gdg.whatssue.service.dto.CheckNumDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // final, notnal이 붙은 필드를 자동으로 생성자에 넣어줌
@Slf4j
public class AttendanceService {

    private final ApplyOfficialAbsentRepository applyOfficialAbsentRepository;
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository  memberRepository;
    private final AttendanceByUserByScheduleRepository attendanceByUserByScheduleRepository;
    private final Map<Long, Integer> checkNumMap = new HashMap<>();
    //출석 시작
    public Integer startAttendance(Long scheduleId) {
        Integer RandomNum = new Random().nextInt(100, 999);
        checkNumMap.put(scheduleId, RandomNum);
        return RandomNum;
    }
    // 출석 다시하기
    public Integer restartAttendance(Long scheduleId) throws Exception {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        if(scheduleRepository.findById(scheduleId).get().getIsChecked() == true) {
            Integer RandomNum = new Random().nextInt(100, 999);
            checkNumMap.put(scheduleId, RandomNum);
            schedule.setIsChecked(false);
        }else throw new Exception("출석이 시행 된 적이 없는 Schedule입니다. 출석하기 버튼을 이용해 주세요");
        return checkNumMap.get(scheduleId);
    }
    //일정에 대한 출석 인원 반환
    public ResponseEntity getAttendanceResult(Long scheduleId) throws Exception {
        //스케줄 id 에 해당하는 공결 조회
        List<AttendanceByUserBySchedule> atendanceByUserByScheduleList = attendanceByUserByScheduleRepository.findBySchedule_ScheduleId(scheduleId);
        // applyOfficialAbsentList에서 AbsentListDto로 변환
        List<AttendanceStateBySheduleDto> AttendanceStateBySheduleList =  atendanceByUserByScheduleList.stream()
                .map(a -> {
                    if (a != null && a.getMember() != null) {
                        return AttendanceStateBySheduleDto.builder()
                                .memberName(a.getMember().getMemberName())
                                .attendanceType(a.getAttendanceType())
                                .build();
                    }
                    return null; // null인 객체는 건너뛰기
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return ResponseEntity.ok(AttendanceStateBySheduleList);
    }

    // 출석 하기
    public ResponseEntity doAttendance(Long scheduleId, Integer num) throws Exception {
        //어떤 클럽의 schedule을 찾을 것인지 모르니 일단 임시로 1번 user가 속한 클럽의 스케줄을 조회
        Long memberId = 1L;
        if (checkNumMap.get(scheduleId).equals(num)) {
//            AttendanceByUserBySchedule attendance =  AttendanceByUserBySchedule.builder()
//                    .attendanceType("출석")
//                    .member(memberRepository.findById(memberId).get())
//                    .schedule(scheduleRepository.findById(scheduleId).get())
//                    .build();
            AttendanceByUserBySchedule attendance = attendanceByUserByScheduleRepository.findBySchedule_ScheduleIdAndMember_MemberId(scheduleId,memberId);
            attendance.setAttendanceType("출석");
            attendanceByUserByScheduleRepository.save(attendance);
        }else throw new Exception("출석번호가 일치하지 않습니다.");
        return ResponseEntity.ok("출석 완료.");

    }
    // 출석 열기 (출석 시도)
    public ResponseEntity openAttendance(Long scheduleId){
        if(checkNumMap.get(scheduleId) == null){
            return new ResponseEntity("출석이 시작되지 않았습니다.", null, 404);
        }else return ResponseEntity.ok("출석이 시작되었습니다.");
    }
    public ResponseEntity finishAttendance(Long scheduleId){
        checkNumMap.remove(scheduleId);
        if(checkNumMap.get(scheduleId) == null){
            Schedule schedule = scheduleRepository.findById(scheduleId).get();
            schedule.setIsChecked(true);
            scheduleRepository.save(schedule);
            return ResponseEntity.ok("출석이 종료되었습니다.");
        }else return new ResponseEntity("출석이 종료되지 않았습니다.", null, 404);
    }
}





