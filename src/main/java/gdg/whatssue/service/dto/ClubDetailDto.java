package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClubDetailDto {
    private String clubName;
    private String clubInfo;
    private String clubCategory;
}
