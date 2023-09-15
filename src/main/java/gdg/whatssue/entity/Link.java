package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Link {
    private String linkName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkId;

    private String linkUrl;

    @OneToOne
    @JoinColumn(name = "club_id")
    private Club club;

}