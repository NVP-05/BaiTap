package ra.edu.ss08.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss08.model.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    // Additional query methods can be defined here if needed
}
