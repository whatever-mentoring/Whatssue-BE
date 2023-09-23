package gdg.whatssue.mapper;

import gdg.whatssue.entity.ApplyOfficialAbsent;
import gdg.whatssue.service.dto.AbsentRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AbsentRequestMapper extends GenericMapper<AbsentRequestDto, ApplyOfficialAbsent> {
    AbsentRequestMapper INSTANCE = Mappers.getMapper(AbsentRequestMapper.class);

}
