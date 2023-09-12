package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleDetailDto {

    private String scheduleTitle;
    private String scheduleContent;
    private String scheduleDate;
    private String scheduleTime;
}
