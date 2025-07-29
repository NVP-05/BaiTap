package ra.edu.ss15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss15.model.dto.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

