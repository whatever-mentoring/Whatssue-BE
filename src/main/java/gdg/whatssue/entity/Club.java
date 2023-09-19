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

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true) //리스트에서 지우면 테이블상에서도 지워지니 주의
    private List <Admin> adminList;

    @OneToMany(mappedBy = "club", cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List <Member> memberList;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL,orphanRemoval = true)
    private List <Schedule> scheduleList ;

    @OneToOne(mappedBy = "club", cascade = CascadeType.ALL,orphanRemoval = true)
    private Link link;

    //clubMemberMapping 상의 멤버리스트. 추후 memberList에서 V2로 전체 리팩토링?
    @OneToMany(mappedBy = "club")
    private List<ClubMemberMapping> memberListV2;

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

    public void updateClub(String clubName, String clubInfo, String clubCategory) {
        this.clubName = clubName;
        this.clubInfo = clubInfo;
        this.clubCategory = clubCategory;
    }

    public void changeLink(Object o) {
        this.link = (Link) o;
    }
}