package gdg.whatssue.service;

import gdg.whatssue.entity.Schedule;
import gdg.whatssue.repository.ScheduleRepository;
import gdg.whatssue.service.dto.ScheduleDetailDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

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

}
