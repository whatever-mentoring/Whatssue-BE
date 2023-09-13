package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleByDateDto {

    private Long scheduleId;
    private String scheduleTitle;
    private String scheduleDate;
    private String scheduleTime;
    private boolean isChecked;
}
