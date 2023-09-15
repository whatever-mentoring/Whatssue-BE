package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class AbsentListDto {
    private String applyOfficialAbsentId;
    private String scheduleId;
    private String absentReason;
    private String absentDate;
    private String absentIsAccepted;

}
