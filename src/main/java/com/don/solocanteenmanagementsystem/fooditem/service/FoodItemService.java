package com.don.solocanteenmanagementsystem.fooditem.service;

import com.don.solocanteenmanagementsystem.fooditem.dto.FoodItemRequestDto;
import com.don.solocanteenmanagementsystem.fooditem.dto.FoodItemResponseDto;

import java.util.List;

public interface FoodItemService {

    void saveFoodItem(FoodItemRequestDto foodItemRequestDto);

    List<FoodItemResponseDto> getAllFoodByCategoryName(String name);
}
