package ra.edu.ss08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss08.model.entity.Ingredient;
import ra.edu.ss08.repository.IngredientRepository;

import java.util.List;

@Service
public class IngredientServiceImp implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Override
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("khong ton tai nguyen lieu"));
    }

    @Override
    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient update(Long id, Ingredient updated) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nguyen lieu khong ton tai"));
        return ingredientRepository.save(updated);
    }

    @Override
    public void delete(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nguyen lieu khong ton tai"));
        ingredientRepository.delete(ingredient);
    }
}
