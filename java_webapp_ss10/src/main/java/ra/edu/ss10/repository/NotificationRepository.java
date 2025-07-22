package ra.edu.ss10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss10.model.entity.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByAccountId(Long accountId);
}