package gdg.whatssue.mapper;

import gdg.whatssue.entity.MoneyBook;
import gdg.whatssue.service.dto.AccountBookCreateDto;
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
public class MoneyBookCreateMapperImpl implements MoneyBookCreateMapper {

    @Override
    public AccountBookCreateDto toDTO(MoneyBook entity) {
        if ( entity == null ) {
            return null;
        }

        AccountBookCreateDto.AccountBookCreateDtoBuilder accountBookCreateDto = AccountBookCreateDto.builder();

        accountBookCreateDto.bookTitle( entity.getBookTitle() );
        if ( entity.getBookAmount() != null ) {
            accountBookCreateDto.bookAmount( entity.getBookAmount().toString() );
        }
        if ( entity.getTotalPaidAmount() != null ) {
            accountBookCreateDto.totalPaidAmount( entity.getTotalPaidAmount().toString() );
        }

        return accountBookCreateDto.build();
    }

    @Override
    public MoneyBook toEntity(AccountBookCreateDto dto) {
        if ( dto == null ) {
            return null;
        }

        MoneyBook.MoneyBookBuilder moneyBook = MoneyBook.builder();

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
            list1.add( moneyBookToAccountBookListDto( moneyBook ) );
        }

        return list1;
    }

    @Override
    public ArrayList<MoneyBook> toEntityList(List<AccountBookCreateDto> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<MoneyBook> arrayList = new ArrayList<MoneyBook>();
        for ( AccountBookCreateDto accountBookCreateDto : list ) {
            arrayList.add( toEntity( accountBookCreateDto ) );
        }

        return arrayList;
    }

    @Override
    public void updateFromDto(AccountBookCreateDto dto, MoneyBook entity) {
        if ( dto == null ) {
            return;
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

    protected AccountBookListDto moneyBookToAccountBookListDto(MoneyBook moneyBook) {
        if ( moneyBook == null ) {
            return null;
        }

        AccountBookListDto.AccountBookListDtoBuilder accountBookListDto = AccountBookListDto.builder();

        accountBookListDto.moneyBookId( moneyBook.getMoneyBookId() );
        accountBookListDto.bookTitle( moneyBook.getBookTitle() );
        if ( moneyBook.getBookAmount() != null ) {
            accountBookListDto.bookAmount( moneyBook.getBookAmount().toString() );
        }
        if ( moneyBook.getTotalPaidAmount() != null ) {
            accountBookListDto.totalPaidAmount( moneyBook.getTotalPaidAmount().toString() );
        }

        return accountBookListDto.build();
    }
}
