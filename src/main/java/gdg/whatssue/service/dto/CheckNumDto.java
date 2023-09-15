package gdg.whatssue.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "인증번호 DTO")
public class CheckNumDto {
    @Schema(description = "인증번호", example = "479")
    private Integer number;
}
