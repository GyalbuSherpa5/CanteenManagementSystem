package com.don.solocanteenmanagementsystem.category.repository;

import com.don.solocanteenmanagementsystem.category.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
    Optional<FoodCategory> findFoodCategoryByName(String name);
}
