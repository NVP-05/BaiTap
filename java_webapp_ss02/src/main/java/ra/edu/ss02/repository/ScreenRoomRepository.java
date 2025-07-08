package ra.edu.ss02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss02.entity.ScreenRoom;

import java.util.List;

public interface ScreenRoomRepository extends JpaRepository<ScreenRoom, Long> {
    List<ScreenRoom> findByNameContainingIgnoreCase(String name);
    List<ScreenRoom> findByTheaterId(Long theaterId);
}
