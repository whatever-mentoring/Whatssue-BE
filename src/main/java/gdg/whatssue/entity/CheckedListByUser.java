package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class CheckedListByUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long checkedListByUserId;

    private Integer checkedCount;
    private Integer absentCount;
    private Integer officialAbsentCount;

    @OneToOne(mappedBy = "checkedListByUser")
    private Member member;
}
