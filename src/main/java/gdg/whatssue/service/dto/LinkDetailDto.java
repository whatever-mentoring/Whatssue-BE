package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LinkDetailDto {
    private String linkName;
    private String linkUrl;
    private String clubId;
    private String clubName;
    private String clubInfo;
    private String clubCategory;
}
