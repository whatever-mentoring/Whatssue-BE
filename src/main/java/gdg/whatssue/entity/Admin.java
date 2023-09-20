package gdg.whatssue.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Entity
@Getter
@RequiredArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long adminId;

    private String role; // owner, admin

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Admin(String role, Club club, Member member) {
        this.role = role;
        this.club = club;
        this.member = member;
    }
}