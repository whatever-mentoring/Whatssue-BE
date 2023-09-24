package gdg.whatssue.repository;

import gdg.whatssue.entity.CheckedListByUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckedListByUserRepository extends JpaRepository<gdg.whatssue.entity.CheckedListByUser, Long> {
    CheckedListByUser findByClubMemberMapping_ClubMemberMappingId(Long clubMemberMappingId);
}
