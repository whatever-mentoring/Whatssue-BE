package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleByMonthDto {

    private Long scheduleId;
    private String scheduleTitle;
    private String scheduleDate;
    private boolean isChecked;
}
