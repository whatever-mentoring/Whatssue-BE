package gdg.whatssue.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "member_NickName")
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

    @OneToOne
    @JoinColumn(name = "checked_list_by_user_id")
    private CheckedListByUser checkedListByUser;


}
