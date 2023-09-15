package gdg.whatssue.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "읿별 일정 DTO")
public class ScheduleByDateDto {

    private Long scheduleId;
    private String scheduleTitle;
    private String scheduleDate;
    private String scheduleTime;
    private boolean isChecked;
}
