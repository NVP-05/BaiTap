package ra.edu.ss15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss15.model.dto.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
    boolean existsByUserIdAndProductId(Long userId, Long productId);
}
