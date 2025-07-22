package ra.edu.ss10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss10.model.entity.TransactionCredit;

public interface TransactionCreditRepository extends JpaRepository<TransactionCredit, Long> {
}