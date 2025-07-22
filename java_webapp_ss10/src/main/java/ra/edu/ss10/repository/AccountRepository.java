package ra.edu.ss10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss10.model.entity.Account;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
