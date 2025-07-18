package ra.edu.ss08.service;

import ra.edu.ss08.model.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> findAll();
    Ingredient findById(Long id);
    Ingredient create(Ingredient ingredient);
    Ingredient update(Long id, Ingredient updated);
    void delete(Long id);

}
