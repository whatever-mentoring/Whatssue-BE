package gdg.whatssue.service.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class AbsentRequestDto {

    String absentReason;
    String absentDate;

    @Builder
    public AbsentRequestDto(String absentReason, String absentDate) {
        this.absentReason = absentReason;
        this.absentDate = absentDate;
    }
}
