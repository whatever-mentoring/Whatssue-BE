package gdg.whatssue.repository;

import gdg.whatssue.entity.MoneyBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoneyBookRepository extends JpaRepository<MoneyBook,Long> {
    List<MoneyBook> findAllByClubId(Long clubId);
    Optional<MoneyBook> findByMoneyBookId(Long bookId);
}
