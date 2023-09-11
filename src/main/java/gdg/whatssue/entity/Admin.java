package gdg.whatssue.entity;

import jakarta.persistence.*;

import lombok.Getter;

@Entity
@Getter
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long adminId;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

}
