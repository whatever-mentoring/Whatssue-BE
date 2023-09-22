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
    date = "2023-09-22T00:08:24+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ScheduleDetailMapperImpl implements ScheduleDetailMapper {

    @Override
    public ScheduleDetailDto toDTO(Schedule entity) {
        if ( entity == null ) {
            return null;
        }

        ScheduleDetailDto.ScheduleDetailDtoBuilder scheduleDetailDto = ScheduleDetailDto.builder();

        scheduleDetailDto.scheduleTitle( entity.getScheduleTitle() );
        scheduleDetailDto.scheduleContent( entity.getScheduleContent() );
        if ( entity.getScheduleDate() != null ) {
            scheduleDetailDto.scheduleDate( DateTimeFormatter.ISO_LOCAL_DATE.format( entity.getScheduleDate() ) );
        }
        if ( entity.getScheduleTime() != null ) {
            scheduleDetailDto.scheduleTime( DateTimeFormatter.ISO_LOCAL_TIME.format( entity.getScheduleTime() ) );
        }

        return scheduleDetailDto.build();
    }

    @Override
    public Schedule toEntity(ScheduleDetailDto dto) {
        if ( dto == null ) {
            return null;
        }

        Schedule.ScheduleBuilder schedule = Schedule.builder();

        schedule.scheduleTitle( dto.getScheduleTitle() );
        schedule.scheduleContent( dto.getScheduleContent() );
        schedule.scheduleDate( dto.getScheduleDate() );
        schedule.scheduleTime( dto.getScheduleTime() );

        return schedule.build();
    }

    @Override
    public ArrayList<ScheduleDetailDto> toDtoList(List<Schedule> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<ScheduleDetailDto> arrayList = new ArrayList<ScheduleDetailDto>();
        for ( Schedule schedule : list ) {
            arrayList.add( toDTO( schedule ) );
        }

        return arrayList;
    }

    @Override
    public ArrayList<Schedule> toEntityList(List<ScheduleDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<Schedule> arrayList = new ArrayList<Schedule>();
        for ( ScheduleDetailDto scheduleDetailDto : list ) {
            arrayList.add( toEntity( scheduleDetailDto ) );
        }

        return arrayList;
    }

    @Override
    public void updateFromDto(ScheduleDetailDto dto, Schedule entity) {
        if ( dto == null ) {
            return;
        }
    }
}
