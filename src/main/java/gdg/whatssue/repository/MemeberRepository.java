package gdg.whatssue.repository;

import gdg.whatssue.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemeberRepository extends JpaRepository<Member, Long> {
}
