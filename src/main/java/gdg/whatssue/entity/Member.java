package gdg.whatssue.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "member_NickName",unique = true)
    private String memberNickName;

    @Column(name = "member_pw")
    private String memberPw;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "member_phone")
    private String memberPhone;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToOne(mappedBy = "member")
    private CheckedListByUser checkedListByUser;

    @OneToOne(mappedBy = "member")
    private Admin admin;

    @OneToMany(mappedBy = "member")
    private List<Deposit> depositList;

    @OneToMany(mappedBy = "member")
    private List<ClubMemberMapping> clubList;

    @Builder
    public Member(String memberNickName, String memberPw, String memberName, String memberEmail, String memberPhone) {
        this.memberNickName = memberNickName;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
    }
}