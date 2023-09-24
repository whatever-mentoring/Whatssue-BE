package gdg.whatssue.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ClubMemberMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubMemberMappingId;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "club_member_mapping")
    private CheckedListByUser checkedListByUser;

}
