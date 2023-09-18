package gdg.whatssue.mapper;

import gdg.whatssue.entity.Schedule;
import gdg.whatssue.service.dto.ScheduleDetailDto;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-18T16:20:04+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ScheduleDetailMapperImpl implements ScheduleDetailMapper {

    @Override
    public ScheduleDetailDto toDTO(Schedule arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ScheduleDetailDto.ScheduleDetailDtoBuilder scheduleDetailDto = ScheduleDetailDto.builder();

        scheduleDetailDto.scheduleTitle( arg0.getScheduleTitle() );
        scheduleDetailDto.scheduleContent( arg0.getScheduleContent() );
        if ( arg0.getScheduleDate() != null ) {
            scheduleDetailDto.scheduleDate( DateTimeFormatter.ISO_LOCAL_DATE.format( arg0.getScheduleDate() ) );
        }
        if ( arg0.getScheduleTime() != null ) {
            scheduleDetailDto.scheduleTime( DateTimeFormatter.ISO_LOCAL_TIME.format( arg0.getScheduleTime() ) );
        }

        return scheduleDetailDto.build();
    }

    @Override
    public Schedule toEntity(ScheduleDetailDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Schedule.ScheduleBuilder schedule = Schedule.builder();

        schedule.scheduleTitle( arg0.getScheduleTitle() );
        schedule.scheduleContent( arg0.getScheduleContent() );
        schedule.scheduleDate( arg0.getScheduleDate() );
        schedule.scheduleTime( arg0.getScheduleTime() );

        return schedule.build();
    }

    @Override
    public ArrayList<ScheduleDetailDto> toDtoList(List<Schedule> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ArrayList<ScheduleDetailDto> arrayList = new ArrayList<ScheduleDetailDto>();
        for ( Schedule schedule : arg0 ) {
            arrayList.add( toDTO( schedule ) );
        }

        return arrayList;
    }

    @Override
    public ArrayList<Schedule> toEntityList(List<ScheduleDetailDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ArrayList<Schedule> arrayList = new ArrayList<Schedule>();
        for ( ScheduleDetailDto scheduleDetailDto : arg0 ) {
            arrayList.add( toEntity( scheduleDetailDto ) );
        }

        return arrayList;
    }

    @Override
    public void updateFromDto(ScheduleDetailDto arg0, Schedule arg1) {
        if ( arg0 == null ) {
            return;
        }
    }
}
