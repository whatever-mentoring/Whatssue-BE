package gdg.whatssue.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@NoArgsConstructor
public class LinkInfoDto {
    @JsonProperty("linkName")
    String linkName;

}
