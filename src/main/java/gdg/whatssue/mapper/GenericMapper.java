package gdg.whatssue.mapper;


import gdg.whatssue.service.dto.AccountBookListDto;
import gdg.whatssue.service.dto.ScheduleDetailDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;

public interface GenericMapper<DTO,Entity> {
    DTO toDTO(Entity entity);
    Entity toEntity(DTO dto);
    List<AccountBookListDto> toDtoList(List<Entity> list);
    ArrayList<Entity> toEntityList(List<DTO> list);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(DTO dto, @MappingTarget Entity entity);


}
