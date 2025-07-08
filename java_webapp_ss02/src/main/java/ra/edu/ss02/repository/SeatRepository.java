package ra.edu.ss02.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss02.entity.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findBySeatNumber(String seatNumber);
    List<Seat> findByScreenRoomId(Long screenRoomId);
}