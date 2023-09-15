package gdg.whatssue.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "일정 상세 요청DTO")
public class ScheduleDetailDto {

    private String scheduleTitle;
    private String scheduleContent;
    private String scheduleDate;
    private String scheduleTime;

}