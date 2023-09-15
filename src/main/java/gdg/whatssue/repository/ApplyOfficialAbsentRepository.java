package gdg.whatssue.repository;

import gdg.whatssue.entity.ApplyOfficialAbsent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyOfficialAbsentRepository extends JpaRepository<ApplyOfficialAbsent, Long> {
}
