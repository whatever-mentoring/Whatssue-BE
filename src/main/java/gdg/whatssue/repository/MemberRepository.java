package gdg.whatssue.repository;

import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.Member;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberNickName(String loginId);
    List<Member> findAllByClub(Club club);
}
