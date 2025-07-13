package com.data.javarest04.service;

import com.data.javarest04.entity.FoodItem;
import com.data.javarest04.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService implements IService<FoodItem,Long> {
    @Autowired
    private FoodItemRepository foodItemRepository;
    @Override
    public FoodItem add(FoodItem t) {
        return foodItemRepository.save(t);
    }
    @Override
    public FoodItem update(FoodItem t) {
        return foodItemRepository.save(t);
    }
    @Override
    public void delete(Long id) {
        foodItemRepository.deleteById(id);
    }
    @Override
    public FoodItem findById(Long id) {
        return foodItemRepository.findById(id).orElse(null);
    }
    @Override
    public Page<FoodItem> getAll(Pageable pageable) {
        return foodItemRepository.findAll(pageable);
    }
    public Page<FoodItem> findByName(String name,String category, Pageable pageable) {
        return foodItemRepository.searchByNameAndCategory(name,category, pageable);
    }
}
