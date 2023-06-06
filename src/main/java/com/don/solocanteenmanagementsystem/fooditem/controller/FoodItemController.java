package com.don.solocanteenmanagementsystem.fooditem.controller;

import com.don.solocanteenmanagementsystem.fooditem.dto.FoodItemRequestDto;
import com.don.solocanteenmanagementsystem.fooditem.dto.FoodItemResponseDto;
import com.don.solocanteenmanagementsystem.fooditem.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping("/foodItem")
    public String saveFoodItem(@RequestBody FoodItemRequestDto foodItemRequestDto) {
        foodItemService.saveFoodItem(foodItemRequestDto);
        return "food Item saved successfully";
    }

    @GetMapping("/foodItem/{name}")
    public List<FoodItemResponseDto> getAllFoodItemByCategoryName(@PathVariable String name) {
        return foodItemService.getAllFoodByCategoryName(name);
    }

}
