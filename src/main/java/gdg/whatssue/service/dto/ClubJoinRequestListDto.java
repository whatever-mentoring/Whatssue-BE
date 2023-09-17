package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClubJoinRequestListDto {

    private Long clubJoinRequestId;
    private String userName;
    private String requestDate;
}
