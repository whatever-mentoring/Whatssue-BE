package gdg.whatssue.repository;

import gdg.whatssue.entity.AttendanceByUserBySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceByUserByScheduleRepository extends JpaRepository<AttendanceByUserBySchedule, Long> {
    AttendanceByUserBySchedule findBySchedule_ScheduleIdAndMember_MemberId(Long scheduleId,Long MemberId);
}
