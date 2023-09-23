package gdg.whatssue.mapper;

import gdg.whatssue.entity.MoneyBook;
import gdg.whatssue.service.dto.AccountBookListDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-23T01:21:32+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class MoneyBookListMapperImpl implements MoneyBookListMapper {

    @Override
    public AccountBookListDto toDTO(MoneyBook entity) {
        if ( entity == null ) {
            return null;
        }

        AccountBookListDto.AccountBookListDtoBuilder accountBookListDto = AccountBookListDto.builder();

        accountBookListDto.moneyBookId( entity.getMoneyBookId() );
        accountBookListDto.bookTitle( entity.getBookTitle() );
        if ( entity.getBookAmount() != null ) {
            accountBookListDto.bookAmount( entity.getBookAmount().toString() );
        }
        if ( entity.getTotalPaidAmount() != null ) {
            accountBookListDto.totalPaidAmount( entity.getTotalPaidAmount().toString() );
        }

        return accountBookListDto.build();
    }

    @Override
    public MoneyBook toEntity(AccountBookListDto dto) {
        if ( dto == null ) {
            return null;
        }

        MoneyBook.MoneyBookBuilder moneyBook = MoneyBook.builder();

        moneyBook.moneyBookId( dto.getMoneyBookId() );
        moneyBook.bookTitle( dto.getBookTitle() );
        if ( dto.getBookAmount() != null ) {
            moneyBook.bookAmount( new BigDecimal( dto.getBookAmount() ) );
        }
        if ( dto.getTotalPaidAmount() != null ) {
            moneyBook.totalPaidAmount( new BigDecimal( dto.getTotalPaidAmount() ) );
        }

        return moneyBook.build();
    }

    @Override
    public List<AccountBookListDto> toDtoList(List<MoneyBook> list) {
        if ( list == null ) {
            return null;
        }

        List<AccountBookListDto> list1 = new ArrayList<AccountBookListDto>( list.size() );
        for ( MoneyBook moneyBook : list ) {
            list1.add( toDTO( moneyBook ) );
        }

        return list1;
    }

    @Override
    public ArrayList<MoneyBook> toEntityList(List<AccountBookListDto> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<MoneyBook> arrayList = new ArrayList<MoneyBook>();
        for ( AccountBookListDto accountBookListDto : list ) {
            arrayList.add( toEntity( accountBookListDto ) );
        }

        return arrayList;
    }

    @Override
    public void updateFromDto(AccountBookListDto dto, MoneyBook entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getMoneyBookId() != null ) {
            entity.setMoneyBookId( dto.getMoneyBookId() );
        }
        if ( dto.getBookTitle() != null ) {
            entity.setBookTitle( dto.getBookTitle() );
        }
        if ( dto.getBookAmount() != null ) {
            entity.setBookAmount( new BigDecimal( dto.getBookAmount() ) );
        }
        if ( dto.getTotalPaidAmount() != null ) {
            entity.setTotalPaidAmount( new BigDecimal( dto.getTotalPaidAmount() ) );
        }
    }
}
