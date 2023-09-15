package gdg.whatssue.repository;

import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByClub(Club club);

    //clubId 와 scheduleId 에 해당하는 Schedule 가져오기
    @Query("SELECT s FROM Schedule s WHERE s.club.ClubId = :clubId AND s.scheduleId = :scheduleId")
    Schedule findByClubIdAndScheduleId(@Param("clubId") Long clubId, @Param("scheduleId") Long scheduleId);

    //Schedule findByClubIdAndScheduleId(Long clubId, Long scheduleId);
    @Query("SELECT s FROM Schedule s WHERE s.club.ClubId = :clubId")
    List<Schedule> findAllByClubId(@Param("clubId") Long clubId);
}
