package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubMemberListDto {

    private Long memberId;
    private String memberName;
    private int checkedCount;
    private int absentCount;
    private int officialAbsentCount;
}
