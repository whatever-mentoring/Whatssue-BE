package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Club {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long ClubId ;

    private String clubName;
    private String clubInfo;
    private String clubCategory;

    @OneToMany(mappedBy = "club")
    private List <Admin> adminList;

    @OneToMany(mappedBy = "club")
    private List <Member> memberList;

    @OneToMany(mappedBy = "club")
    private List <Schedule> scheduleList ;

    @OneToOne(mappedBy = "club")
    private Link link;
}
