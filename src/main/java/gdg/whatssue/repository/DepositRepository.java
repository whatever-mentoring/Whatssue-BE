package gdg.whatssue.repository;

import gdg.whatssue.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long>{

}