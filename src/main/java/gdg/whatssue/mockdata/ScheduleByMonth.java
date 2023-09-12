package gdg.whatssue.mockdata;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ScheduleByMonth {
    public Long scheduleId;
    public String scheduleTitle;
    public String scheduleDate;
}
