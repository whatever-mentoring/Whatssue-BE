package gdg.whatssue.mockdata;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Schedule {

    private String scheduleTitle;
    private String scheduleContent;
    private String scheduleDate;
    private String scheduleTime;
    private boolean isChecked;
}
