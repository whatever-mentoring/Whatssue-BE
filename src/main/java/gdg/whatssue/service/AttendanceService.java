package gdg.whatssue.service;

import gdg.whatssue.entity.AttendanceByUserBySchedule;
import gdg.whatssue.repository.ApplyOfficialAbsentRepository;
import gdg.whatssue.repository.AttendanceByUserByScheduleRepository;
import gdg.whatssue.repository.MemberRepository;
import gdg.whatssue.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
        Integer RandomNum = new Random().nextInt(900)+100;
        checkNumMap.put(scheduleId, RandomNum);
        return RandomNum;
    }

    // 출석 하기
    public void doAttendance(Long scheduleId, Integer checkNum) throws Exception {
        //어떤 클럽의 schedule을 찾을 것인지 모르니 일단 임시로 1번 user가 속한 클럽의 스케줄을 조회
        Long memberId = 1L;
        if (checkNumMap.get(scheduleId).equals(checkNum)) {
             AttendanceByUserBySchedule attendance =  AttendanceByUserBySchedule.builder()
                    .attendanceType("출석")
                    .member(memberRepository.findById(memberId).get())
                    .schedule(scheduleRepository.findById(scheduleId).get())
                    .build();
        }else throw new Exception("출석번호가 일치하지 않습니다.");
    }
    // 출석 열기 (출석 시도)
    public ResponseEntity openAttendance(Long scheduleId){
    if(checkNumMap.get(scheduleId) == null){
            return ResponseEntity.ok("출석이 시작되지 않았습니다.");
        }else return ResponseEntity.ok("출석이 시작되었습니다."); // 요기 리다이렉트로(?) 수정
    }

    public ResponseEntity finishAttendance(Long scheduleId){
        checkNumMap.remove(scheduleId);
        if(checkNumMap.get(scheduleId) == null){
            return ResponseEntity.ok("출석이 종료되었습니다.");
        }else return ResponseEntity.ok("출석이 종료되지 않았습니다.");
    }
}
