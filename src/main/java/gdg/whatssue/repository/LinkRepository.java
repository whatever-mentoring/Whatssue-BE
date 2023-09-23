package gdg.whatssue.repository;

import gdg.whatssue.entity.Club;
import gdg.whatssue.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByLinkUrl(String teamId);
    List<Link> findAllByClub(Club club);

    Link findByLinkId(Long linkId);

    Optional<Link> findByLinkName(String linkName);
}
