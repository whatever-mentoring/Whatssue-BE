package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
public class Withdraw {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long withdrawId;

    private BigDecimal withdrawAmount;
    private LocalDate withdrawDate;
    private LocalTime withdrawTime;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private  Admin admin;

}
