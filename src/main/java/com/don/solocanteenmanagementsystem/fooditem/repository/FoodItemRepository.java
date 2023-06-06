package com.don.solocanteenmanagementsystem.fooditem.repository;

import com.don.solocanteenmanagementsystem.fooditem.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    @Query(value = "select * from food_item where category_id=?", nativeQuery = true)
    List<FoodItem> findAllItemByCategoryId(Long id);

    Optional<FoodItem> findFoodItemByName(String name);

}
