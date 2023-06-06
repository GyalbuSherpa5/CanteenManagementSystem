package com.don.solocanteenmanagementsystem.category.service;

import com.don.solocanteenmanagementsystem.category.dto.FoodCategoryDto;
import com.don.solocanteenmanagementsystem.category.dto.FoodCategoryRequestDto;

import java.util.List;

public interface FoodCategoryService {
    List<FoodCategoryDto> getAllFoodByCategory();

    void saveFoodCategoryByDto(FoodCategoryRequestDto foodCategoryRequestDto);
}
