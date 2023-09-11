package gdg.whatssue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckedListByUser extends JpaRepository<gdg.whatssue.entity.CheckedListByUser, Long> {
}
