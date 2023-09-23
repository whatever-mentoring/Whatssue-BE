package gdg.whatssue.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@NoArgsConstructor
public class AttendanceStateBySheduleDto {
    @Schema(description = "출석 상태", example = "출석")
    private String attendanceType;
    @Schema(description = "회원 이름", example = "김지훈")
    private String memberName;

    @Builder
    public AttendanceStateBySheduleDto(String attendanceType, String memberName) {
        this.attendanceType = attendanceType;
        this.memberName = memberName;
    }
}
