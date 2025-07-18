package ra.edu.ss08.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss08.model.entity.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
