package gdg.whatssue.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "월별 일정 DTO")
public class ScheduleByMonthDto {

    private Long scheduleId;
    private String scheduleTitle;
    private String scheduleDate;
    private String scheduleTime;
}
