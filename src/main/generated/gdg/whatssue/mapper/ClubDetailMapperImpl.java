package gdg.whatssue.mapper;

import gdg.whatssue.entity.Club;
import gdg.whatssue.service.dto.AccountBookListDto;
import gdg.whatssue.service.dto.ClubDetailDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-21T11:50:58+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ClubDetailMapperImpl implements ClubDetailMapper {

    @Override
    public ClubDetailDto toDTO(Club entity) {
        if ( entity == null ) {
            return null;
        }

        ClubDetailDto.ClubDetailDtoBuilder clubDetailDto = ClubDetailDto.builder();

        clubDetailDto.clubName( entity.getClubName() );
        clubDetailDto.clubInfo( entity.getClubInfo() );
        clubDetailDto.clubCategory( entity.getClubCategory() );

        return clubDetailDto.build();
    }

    @Override
    public Club toEntity(ClubDetailDto dto) {
        if ( dto == null ) {
            return null;
        }

        Club.ClubBuilder club = Club.builder();

        club.clubName( dto.getClubName() );
        club.clubInfo( dto.getClubInfo() );
        club.clubCategory( dto.getClubCategory() );

        return club.build();
    }

    @Override
    public ArrayList<ClubDetailDto> toDtoList(List<Club> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<ClubDetailDto> arrayList = new ArrayList<ClubDetailDto>();
        for ( Club club : list ) {
            arrayList.add( toDTO( club ) );
        }

        return arrayList;
    }

    @Override
    public ArrayList<Club> toEntityList(List<ClubDetailDto> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<Club> arrayList = new ArrayList<Club>();
        for ( ClubDetailDto clubDetailDto : arg0 ) {
            arrayList.add( toEntity( clubDetailDto ) );
        }

        return arrayList;
    }

    @Override
    public void updateFromDto(ClubDetailDto dto, Club entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getClubName() != null ) {
            entity.setClubName( dto.getClubName() );
        }
        if ( dto.getClubInfo() != null ) {
            entity.setClubInfo( dto.getClubInfo() );
        }
        if ( dto.getClubCategory() != null ) {
            entity.setClubCategory( dto.getClubCategory() );
        }
    }
}
