package ra.edu.ss09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss09.model.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
