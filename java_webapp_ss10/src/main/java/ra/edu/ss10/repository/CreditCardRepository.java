package ra.edu.ss10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss10.model.entity.CreditCard;

import java.util.Optional;
import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    boolean existsByAccountId(Long accountId);
}
