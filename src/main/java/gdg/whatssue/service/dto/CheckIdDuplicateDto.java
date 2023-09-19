package gdg.whatssue.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckIdDuplicateDto {

    @NotBlank
    String memberNickName;
}
