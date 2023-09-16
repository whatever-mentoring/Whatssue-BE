package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Link {
    private String linkName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkId;

    private String linkUrl;

    @OneToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @Builder
    public Link(String linkName, String linkUrl, Club club) {
        this.linkName = linkName;
        this.linkUrl = linkUrl;
        this.club = club;
    }

}