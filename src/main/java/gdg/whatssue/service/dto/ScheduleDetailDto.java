package gdg.whatssue.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "일정 상세 요청DTO")
public class ScheduleDetailDto {

    @Schema(description = "일정 제목", example = "GDSC 오티")
    private String scheduleTitle;
    @Schema(description ="일정 내용", example = "GDSC 오티에 참여합니다.")
    private String scheduleContent;
    @Schema(description = "일정 날짜", example = "2021-08-28")
    private String scheduleDate;
    @Schema(description = "일정 시간", example = "10:00:00")
    private String scheduleTime;

}