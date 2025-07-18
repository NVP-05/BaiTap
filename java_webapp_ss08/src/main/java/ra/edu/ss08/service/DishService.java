package ra.edu.ss08.service;

import ra.edu.ss08.model.entity.Dish;

import java.util.List;

public interface DishService {
    List<Dish> getAllDishes();
    Dish getDishById(Long id);
    Dish createDish(Dish dish);
    Dish updateDish(Long id, Dish dish);
    void deleteDish(Long id);
}
