package com.don.solocanteenmanagementsystem.category.controller;

import com.don.solocanteenmanagementsystem.category.dto.FoodCategoryDto;
import com.don.solocanteenmanagementsystem.category.dto.FoodCategoryRequestDto;
import com.don.solocanteenmanagementsystem.category.service.FoodCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodCategoryController {

    private final FoodCategoryService foodCategoryService;

    @PostMapping("/foodCategory")
    public String createFoodCategory(@RequestBody FoodCategoryRequestDto foodCategoryRequestDto) {
        foodCategoryService.saveFoodCategoryByDto(foodCategoryRequestDto);
        return "food category saved successfully";
    }

    @GetMapping("/foodCategory")
    public List<FoodCategoryDto> getAllFoodCategoryWithItems() {
        return foodCategoryService.getAllFoodByCategory();
    }

}
