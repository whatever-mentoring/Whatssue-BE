package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CheckedListByUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long checkedListByUserId;

    private int checkedCount;
    private int absentCount;
    private int officialAbsentCount;

    @OneToOne
    @JoinColumn(name = "club_member_mapping_id")
    private ClubMemberMapping club_member_mapping;
}
