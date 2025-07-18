package ra.edu.ss08.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss08.model.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional query methods can be defined here if needed
}
