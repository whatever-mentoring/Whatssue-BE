package gdg.whatssue.mapper;

import gdg.whatssue.entity.Club;
import gdg.whatssue.service.dto.ClubDetailDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-20T00:46:41+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ClubDetailMapperImpl implements ClubDetailMapper {

    @Override
    public ClubDetailDto toDTO(Club arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ClubDetailDto.ClubDetailDtoBuilder clubDetailDto = ClubDetailDto.builder();

        clubDetailDto.clubName( arg0.getClubName() );
        clubDetailDto.clubInfo( arg0.getClubInfo() );
        clubDetailDto.clubCategory( arg0.getClubCategory() );

        return clubDetailDto.build();
    }

    @Override
    public Club toEntity(ClubDetailDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Club.ClubBuilder club = Club.builder();

        club.clubName( arg0.getClubName() );
        club.clubInfo( arg0.getClubInfo() );
        club.clubCategory( arg0.getClubCategory() );

        return club.build();
    }

    @Override
    public ArrayList<ClubDetailDto> toDtoList(List<Club> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ArrayList<ClubDetailDto> arrayList = new ArrayList<ClubDetailDto>();
        for ( Club club : arg0 ) {
            arrayList.add( toDTO( club ) );
        }

        return arrayList;
    }

    @Override
    public ArrayList<Club> toEntityList(List<ClubDetailDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        ArrayList<Club> arrayList = new ArrayList<Club>();
        for ( ClubDetailDto clubDetailDto : arg0 ) {
            arrayList.add( toEntity( clubDetailDto ) );
        }

        return arrayList;
    }

    @Override
    public void updateFromDto(ClubDetailDto arg0, Club arg1) {
        if ( arg0 == null ) {
            return;
        }

        if ( arg0.getClubName() != null ) {
            arg1.setClubName( arg0.getClubName() );
        }
        if ( arg0.getClubInfo() != null ) {
            arg1.setClubInfo( arg0.getClubInfo() );
        }
        if ( arg0.getClubCategory() != null ) {
            arg1.setClubCategory( arg0.getClubCategory() );
        }
    }
}
