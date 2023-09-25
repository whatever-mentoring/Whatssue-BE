package gdg.whatssue.repository;

import gdg.whatssue.entity.Claim;
import gdg.whatssue.entity.ClaimResult;
import gdg.whatssue.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaimResultRepository extends JpaRepository<ClaimResult, Long> {
    Optional<ClaimResult> findByMember_memberId(Long memberId);
    Optional<ClaimResult> findByMemberAndClaim(Member member, Claim claim);
    List<ClaimResult> findAllByClaim(Claim claim);
}
