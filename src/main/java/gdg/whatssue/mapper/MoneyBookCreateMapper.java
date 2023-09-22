package gdg.whatssue.mapper;

import gdg.whatssue.entity.MoneyBook;
import gdg.whatssue.service.dto.AccountBookCreateDto;
import gdg.whatssue.service.dto.AccountBookListDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface MoneyBookCreateMapper extends GenericMapper<AccountBookCreateDto, MoneyBook>{
    MoneyBookCreateMapper INSTANCE = Mappers.getMapper(MoneyBookCreateMapper.class);

}
