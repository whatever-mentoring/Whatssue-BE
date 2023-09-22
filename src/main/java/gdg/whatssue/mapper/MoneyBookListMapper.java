package gdg.whatssue.mapper;

import gdg.whatssue.entity.MoneyBook;
import gdg.whatssue.service.dto.AccountBookListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MoneyBookListMapper extends GenericMapper<AccountBookListDto, MoneyBook> {
    MoneyBookListMapper INSTANCE = Mappers.getMapper(MoneyBookListMapper.class);



}
