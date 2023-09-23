package gdg.whatssue.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LinkResultDto {
    String LinkId;
    String LinkUrl;
    String LinkName;
}
