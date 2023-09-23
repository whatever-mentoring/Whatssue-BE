package gdg.whatssue.entity;


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor

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


    //totalPaidAmount 는 club_id 에 해당하는 bookAmount 들의 총 값을 값이 변경/추가/삭제 될때 마다 트리거해서 저장한다.


    // 생성일시에 대한 기본값 설정
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public void saveClub(Club club) {
        this.club = club;
    }

    public void updateMoneyBook(MoneyBook updateMoneyBook) {

        this.bookTitle = updateMoneyBook.bookTitle;
        this.bookAmount = updateMoneyBook.bookAmount;
    }


}

