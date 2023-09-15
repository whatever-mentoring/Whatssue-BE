package gdg.whatssue.mapper;

import gdg.whatssue.entity.Schedule;
import gdg.whatssue.service.dto.ScheduleDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")//* (componentModel = "spring") 이게 없으면 스프링 bean 으로 등록되지 않으므로 명시해 줘야함.
public interface ScheduleDetailMapper extends GenericMapper<ScheduleDetailDto, Schedule>{
    ScheduleDetailMapper INSTANCE = Mappers.getMapper(ScheduleDetailMapper.class);

}
