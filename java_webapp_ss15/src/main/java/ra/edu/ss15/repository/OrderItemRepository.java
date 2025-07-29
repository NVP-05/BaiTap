package ra.edu.ss15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss15.model.dto.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    boolean existsByOrder_User_IdAndProduct_Id(Long userId, Long productId);
}

