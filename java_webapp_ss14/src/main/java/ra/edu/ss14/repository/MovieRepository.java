package ra.edu.ss14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss14.model.dto.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
