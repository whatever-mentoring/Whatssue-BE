package gdg.whatssue.repository;

import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.MoneyBook;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoneyBookRepository extends JpaRepository<MoneyBook,Long> {
    Optional<MoneyBook> findByMoneyBookId(Long bookId);
    @Query("select m from MoneyBook m where m.club.ClubId = :clubId and m.moneyBookId = :moneyBookId")
    Optional<MoneyBook> findByClub_ClubIdAndMoneyBookId(@Param("clubId") Long clubId, @Param("moneyBookId") Long moneyBookId);

    List<MoneyBook> findAllByClub(Club club);
}
