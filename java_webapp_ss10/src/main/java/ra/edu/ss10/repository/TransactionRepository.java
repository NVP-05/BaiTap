package ra.edu.ss10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss10.model.entity.Transaction;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
