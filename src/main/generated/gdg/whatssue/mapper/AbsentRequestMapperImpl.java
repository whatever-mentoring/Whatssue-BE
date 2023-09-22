package gdg.whatssue.mapper;

import gdg.whatssue.entity.ApplyOfficialAbsent;
import gdg.whatssue.service.dto.AbsentRequestDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-22T00:08:24+0900",
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
    public ArrayList<AbsentRequestDto> toDtoList(List<ApplyOfficialAbsent> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<AbsentRequestDto> arrayList = new ArrayList<AbsentRequestDto>();
        for ( ApplyOfficialAbsent applyOfficialAbsent : list ) {
            arrayList.add( toDTO( applyOfficialAbsent ) );
        }

        return arrayList;
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
}
