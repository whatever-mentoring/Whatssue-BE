package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CheckedListByUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkedListByUserId;

    private int checkedCount;
    private int absentCount;
    private int officialAbsentCount;

    @OneToOne
    @JoinColumn(name = "club_member_mapping_id")
    private ClubMemberMapping clubMemberMapping;


}
