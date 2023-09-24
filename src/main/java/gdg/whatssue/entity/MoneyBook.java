package gdg.whatssue.entity;


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@Setter
@RequiredArgsConstructor
@AllArgsConstructor

@Table(name = "money_book")
public class MoneyBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moneyBookId;

    @OneToOne
    @JoinColumn(name = "club_id")
    private Club club;

    private String bookTitle; // 입출금 제목
    private BigDecimal bookAmount;
    private BigDecimal totalPaidAmount;


    // 생성일시에 대한 기본값 설정
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public void saveMoneyBook(Club club,BigDecimal totalPaidAmount) {
        this.club = club;
        this.totalPaidAmount = totalPaidAmount;
    }

    public void saveClub(Club club){
        this.club = club;
    }
    public void updateMoneyBook(String bookTitle,BigDecimal bookAmount,Club club,BigDecimal totalPaidAmount) {

        this.bookTitle = bookTitle;
        this.bookAmount = bookAmount;
        this.club = club;
        this.totalPaidAmount = totalPaidAmount;
    }


}

