package gdg.whatssue.repository;

import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByClub(Club club);

    List<Schedule> findBySchedule_ClubId(Long clubId);
}
