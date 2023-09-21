package gdg.whatssue.entity;


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
public class MoneyBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moneyBookId;

    @OneToOne
    private Club club;

    private String bookTitle; // 입출금 제목
    private BigDecimal bookAmount;
    private BigDecimal totalPaidAmount;
    private LocalDateTime createAt;
}

