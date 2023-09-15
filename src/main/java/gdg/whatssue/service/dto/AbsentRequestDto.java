package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class AbsentRequestDto {

    String absentReason;
    String absentDate;
}
