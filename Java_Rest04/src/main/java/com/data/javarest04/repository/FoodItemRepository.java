package com.data.javarest04.repository;

import com.data.javarest04.entity.FoodItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    @Query("SELECT f FROM FoodItem f WHERE " +
            "(:name IS NULL OR LOWER(f.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:category IS NULL OR LOWER(f.category) LIKE LOWER(CONCAT('%', :category, '%')))")
    Page<FoodItem> searchByNameAndCategory(@Param("name") String name,
                                           @Param("category") String category,
                                           Pageable pageable);

}
