package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Deposit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long depositId;

    private BigDecimal depositAmount;
    private Date depositDate;
    private Time depositTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
