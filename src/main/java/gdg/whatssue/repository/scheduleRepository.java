package gdg.whatssue.repository;

import gdg.whatssue.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface scheduleRepository extends JpaRepository<Schedule, Long> {
}
