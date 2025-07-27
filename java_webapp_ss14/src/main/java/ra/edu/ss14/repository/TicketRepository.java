package ra.edu.ss14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss14.model.dto.entity.Ticket;
import ra.edu.ss14.model.dto.entity.User;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);
}
