package gdg.whatssue.mapper;

import gdg.whatssue.entity.Club;
import gdg.whatssue.service.dto.ClubDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface ClubDetailMapper extends GenericMapper<ClubDetailDto, Club> {
    ClubDetailMapper INSTANCE = Mappers.getMapper(ClubDetailMapper.class);
}
