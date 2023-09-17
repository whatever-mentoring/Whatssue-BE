package gdg.whatssue.service.dto;

import gdg.whatssue.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class AbsentListDto {
    private String applyOfficialAbsentId;
    private String scheduleId;
    private String scheduleTitle;
    private String absentReason;
    private String absentDate;
    private String absentIsAccepted;
    private String memberId;
    private String memberName;
    private String memberNickName;
    private String memberEmail;
    private String memberPhone;




}
