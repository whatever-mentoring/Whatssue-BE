package gdg.whatssue.mapper;

import gdg.whatssue.entity.MoneyBook;
import gdg.whatssue.service.dto.AccountBookCreateDto;
import gdg.whatssue.service.dto.AccountBookListDto;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface MoneyBookMapper extends GenericMapper<AccountBookCreateDto, MoneyBook>{
    MoneyBookMapper INSTANCE = Mappers.getMapper(MoneyBookMapper.class);

    @Mapping(source = "bookAmount", target = "bookAmount")
    @Mapping(source = "bookDate", target = "bookDate")
    MoneyBook mapDTOToEntity(AccountBookCreateDto dto);

    @Mapping(source = "moneyBookId", target = "moneyBookId")
    @Mapping(source = "bookAmount", target = "bookAmount")
    @Mapping(source = "bookDate", target = "bookDate")
    AccountBookListDto mapEntityToDTO(MoneyBook moneyBook);
}
