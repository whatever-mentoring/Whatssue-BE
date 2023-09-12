package gdg.whatssue.entity;

import jakarta.persistence.*;

import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long adminId;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "admin")
    private List<Withdraw> withdrawList;


}
