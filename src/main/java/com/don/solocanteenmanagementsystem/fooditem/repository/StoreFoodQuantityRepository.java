package com.don.solocanteenmanagementsystem.fooditem.repository;

import com.don.solocanteenmanagementsystem.fooditem.model.StoreFoodQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public interface StoreFoodQuantityRepository extends JpaRepository<StoreFoodQuantity, Long> {

    @Query(value = "select quantity from store_food_quantity where food_id =?1 and customer_id=?2 and food_category_name=?3 and token=?4", nativeQuery = true)
    long findQuantityByFourIds(Long foodId, Long customerId, String foodCategoryName, Long orderId);

    @Query(value = "select token from store_food_quantity", nativeQuery = true)
    List<Long> findAllToken();
}
