package gdg.whatssue.service;

import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.Schedule;
import gdg.whatssue.mapper.ScheduleDetailMapper;
import gdg.whatssue.repository.MemberRepository;
import gdg.whatssue.repository.ScheduleRepository;
import gdg.whatssue.service.dto.ScheduleByDateDto;
import gdg.whatssue.service.dto.ScheduleByMonthDto;
import gdg.whatssue.service.dto.ScheduleDetailDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    //일정 상세 조회
    public ScheduleDetailDto getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);

        if (schedule != null) {
            ScheduleDetailDto scheduleDetailDto = ScheduleDetailDto.builder()
                .scheduleTitle(schedule.getScheduleTitle())
                .scheduleContent(schedule.getScheduleContent())
                .scheduleDate(schedule.getScheduleDate().toString())
                .scheduleTime(schedule.getScheduleTime().toString()).build();

            return scheduleDetailDto;
        }

        return null;
    }

    //월별 일정 조회
    public List<ScheduleByMonthDto> getScheduleByMonth(String yearMonth) {


        //어떤 클럽의 schedule을 찾을 것인지 모르니 일단 임시로 1번 user가 속한 클럽의 스케줄을 조회
        Long userId = 1L;
        Club club = memberRepository.findById(userId).get().getClub();
        List<Schedule> scheduleList = club.getScheduleList();

        List<ScheduleByMonthDto> scheduleListByMonth = scheduleList.stream()
            .filter(s -> s.getScheduleDate().toString().substring(0, 7).equals(yearMonth))
            .map(s-> ScheduleByMonthDto.builder()
                .scheduleId(s.getScheduleId())
                .scheduleTitle(s.getScheduleTitle())
                .scheduleDate(s.getScheduleDate().toString())
                .scheduleTime(s.getScheduleTime().toString())
                .build())
            .collect(Collectors.toList());

        return scheduleListByMonth;
    }

    //일자별 일정 조회
    public List<ScheduleByDateDto> getScheduleByDate(String yearMonthDate) {

        //어떤 클럽의 schedule을 찾을 것인지 모르니 일단 임시로 1번 user가 속한 클럽의 스케줄을 조회
        Long userId = 1L;
        Club club = memberRepository.findById(userId).get().getClub();
        List<Schedule> scheduleList = club.getScheduleList();

        List<ScheduleByDateDto> scheduleListByMonth = scheduleList.stream()
            .filter(s -> s.getScheduleDate().toString().equals(yearMonthDate))
            .map(s-> ScheduleByDateDto.builder()
                .scheduleId(s.getScheduleId())
                .scheduleTitle(s.getScheduleTitle())
                .scheduleDate(s.getScheduleDate().toString())
                .scheduleTime(s.getScheduleTime().toString())
                .isChecked(s.getIsChecked()).build())
            .collect(Collectors.toList());

        return scheduleListByMonth;
    }

    public ResponseEntity deleteSchedule(Long scheduleId) {
        Long clubId = 1L;
        //clubId와 scheduleId 둘 다에 해당하는 schedule 가져오기
        Schedule schedule = scheduleRepository.findByClubIdAndScheduleId(clubId, scheduleId);
        //Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);
        if (schedule == null) {
            return ResponseEntity.badRequest().body("존재하지 않는 일정입니다.");
        }
        scheduleRepository.delete(schedule);
        return ResponseEntity.ok().body("일정이 삭제되었습니다.");

    }

    public ResponseEntity updateSchedule(Long scheduleId, ScheduleDetailDto scheduleDetailDto) {
        Long clubId = 1L;
        //clubId와 scheduleId 둘 다에 해당하는 schedule 가져오기
        Schedule schedule = scheduleRepository.findByClubIdAndScheduleId(clubId, scheduleId);
        if (schedule == null) {
            return ResponseEntity.badRequest().body("존재하지 않는 일정입니다.");
        }

        schedule.updateSchedule(scheduleDetailDto.getScheduleTitle(), scheduleDetailDto.getScheduleContent(),
            LocalDate.parse(scheduleDetailDto.getScheduleDate()), LocalTime.parse(scheduleDetailDto.getScheduleTime()));
        return ResponseEntity.ok().body("일정이 수정되었습니다.");


    }
    // 상세 일정 등록
    public void createSchedule(ScheduleDetailDto dto)  {
        Schedule schedule = Schedule.builder()
            .scheduleTitle(dto.getScheduleTitle())
            .scheduleContent(dto.getScheduleContent())
            .scheduleDate(dto.getScheduleDate())
            .scheduleTime(dto.getScheduleTime())
            .build();
        try{scheduleRepository.save(schedule);
        }catch ( Exception e){
            throw new RuntimeException("일정 등록에 실패하였습니다.");
        }

    }


}
