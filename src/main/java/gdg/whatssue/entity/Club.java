package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long ClubId ;

    private String clubName;
    private String clubInfo;
    private String clubCategory;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List <Admin> adminList;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List <Member> memberList;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List <Schedule> scheduleList ;

    @OneToOne(mappedBy = "club", cascade = CascadeType.ALL)
    private Link link;

    @Builder
    public Club(String clubName, String clubInfo, String clubCategory, List<Admin> adminList, List<Member> memberList, List<Schedule> scheduleList, Link link) {
        this.clubName = clubName;
        this.clubInfo = clubInfo;
        this.clubCategory = clubCategory;
        this.adminList = adminList;
        this.memberList = memberList;
        this.scheduleList = scheduleList;
        this.link = link;
    }
}