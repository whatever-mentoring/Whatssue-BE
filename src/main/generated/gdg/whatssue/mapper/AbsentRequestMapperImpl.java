package gdg.whatssue.mapper;

import gdg.whatssue.entity.ApplyOfficialAbsent;
import gdg.whatssue.service.dto.AbsentRequestDto;
import gdg.whatssue.service.dto.AccountBookListDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class AbsentRequestMapperImpl implements AbsentRequestMapper {

    @Override
    public AbsentRequestDto toDTO(ApplyOfficialAbsent entity) {
        if ( entity == null ) {
            return null;
        }

        AbsentRequestDto.AbsentRequestDtoBuilder absentRequestDto = AbsentRequestDto.builder();

        absentRequestDto.absentReason( entity.getAbsentReason() );
        if ( entity.getAbsentDate() != null ) {
            absentRequestDto.absentDate( DateTimeFormatter.ISO_LOCAL_DATE.format( entity.getAbsentDate() ) );
        }

        return absentRequestDto.build();
    }

    @Override
    public ApplyOfficialAbsent toEntity(AbsentRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        ApplyOfficialAbsent.ApplyOfficialAbsentBuilder applyOfficialAbsent = ApplyOfficialAbsent.builder();

        applyOfficialAbsent.absentReason( dto.getAbsentReason() );
        if ( dto.getAbsentDate() != null ) {
            applyOfficialAbsent.absentDate( LocalDate.parse( dto.getAbsentDate() ) );
        }

        return applyOfficialAbsent.build();
    }

    @Override
    public List<AccountBookListDto> toDtoList(List<ApplyOfficialAbsent> list) {
        if ( list == null ) {
            return null;
        }

        List<AccountBookListDto> list1 = new ArrayList<AccountBookListDto>( list.size() );
        for ( ApplyOfficialAbsent applyOfficialAbsent : list ) {
            list1.add( applyOfficialAbsentToAccountBookListDto( applyOfficialAbsent ) );
        }

        return list1;
    }

    @Override
    public ArrayList<ApplyOfficialAbsent> toEntityList(List<AbsentRequestDto> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<ApplyOfficialAbsent> arrayList = new ArrayList<ApplyOfficialAbsent>();
        for ( AbsentRequestDto absentRequestDto : list ) {
            arrayList.add( toEntity( absentRequestDto ) );
        }

        return arrayList;
    }

    @Override
    public void updateFromDto(AbsentRequestDto dto, ApplyOfficialAbsent entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getAbsentReason() != null ) {
            entity.setAbsentReason( dto.getAbsentReason() );
        }
        if ( dto.getAbsentDate() != null ) {
            entity.setAbsentDate( LocalDate.parse( dto.getAbsentDate() ) );
        }
    }

    protected AccountBookListDto applyOfficialAbsentToAccountBookListDto(ApplyOfficialAbsent applyOfficialAbsent) {
        if ( applyOfficialAbsent == null ) {
            return null;
        }

        AccountBookListDto.AccountBookListDtoBuilder accountBookListDto = AccountBookListDto.builder();

        return accountBookListDto.build();
    }
}
