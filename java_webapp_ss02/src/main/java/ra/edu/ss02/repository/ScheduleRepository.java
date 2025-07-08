package ra.edu.ss02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss02.entity.Schedule;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByMovieId(Long movieId);
    List<Schedule> findByScreenRoomId(Long screenRoomId);
}