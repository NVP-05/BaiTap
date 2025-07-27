package ra.edu.ss14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss14.model.dto.entity.Showtime;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
}
