package gdg.whatssue.repository;

import gdg.whatssue.entity.AttendanceByUserBySchedule;
import gdg.whatssue.entity.Member;
import gdg.whatssue.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceByUserByScheduleRepository extends JpaRepository<AttendanceByUserBySchedule, Long> {
    AttendanceByUserBySchedule findBySchedule_ScheduleIdAndMember_MemberId(Long scheduleId,Long MemberId);
    List<AttendanceByUserBySchedule> findBySchedule_ScheduleId(Long scheduleId);
    AttendanceByUserBySchedule findByMemberAndSchedule(Member member, Schedule schedule);





}
