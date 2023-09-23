package gdg.whatssue.repository;

import gdg.whatssue.entity.Claim;
import gdg.whatssue.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaimRepository extends JpaRepository<Claim,Long> {

    Optional<Claim> findAllByClub_ClubId(Club club);
}
