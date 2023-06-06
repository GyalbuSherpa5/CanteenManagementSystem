package com.don.solocanteenmanagementsystem.category.service;

import com.don.solocanteenmanagementsystem.category.dto.FoodCategoryDto;
import com.don.solocanteenmanagementsystem.category.dto.FoodCategoryRequestDto;
import com.don.solocanteenmanagementsystem.fooditem.exception.FoodItemAlreadyExistException;
import com.don.solocanteenmanagementsystem.category.model.FoodCategory;
import com.don.solocanteenmanagementsystem.category.repository.FoodCategoryRepository;
import com.don.solocanteenmanagementsystem.fooditem.dto.FoodItemResponseDto;
import com.don.solocanteenmanagementsystem.fooditem.enums.FoodStatus;
import com.don.solocanteenmanagementsystem.fooditem.model.FoodItem;
import com.don.solocanteenmanagementsystem.fooditem.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService {
    private final FoodCategoryRepository foodCategoryRepository;
    private final FoodItemRepository foodItemRepository;

    @Override
    public List<FoodCategoryDto> getAllFoodByCategory() {
        List<FoodCategory> foodCategories = foodCategoryRepository.findAll();

        List<FoodCategoryDto> foodCategoryDTOs = new ArrayList<>();

        for (FoodCategory foodCategory : foodCategories) {

            List<FoodItem> foodItemResponseDto = foodCategory.getFoodItems();
            List<FoodItemResponseDto> itemResponseDtoArrayList = new ArrayList<>();

            for (FoodItem foodItem : foodItemResponseDto) {
                FoodItemResponseDto foodItemResponseDto1 = new FoodItemResponseDto();
                foodItemResponseDto1.setName(foodItem.getName());
                foodItemResponseDto1.setDescription(foodItem.getDescription());
                foodItemResponseDto1.setAvailability(foodItem.getAvailability());
                foodItemResponseDto1.setStock(foodItem.getStock());
                foodItemResponseDto1.setPrice(foodItem.getPrice());

                itemResponseDtoArrayList.add(foodItemResponseDto1);
            }

            FoodCategoryDto foodCategoryDto = FoodCategoryDto
                    .builder()
                    .name(foodCategory.getName())
                    .description(foodCategory.getDescription())
                    .foodItems(itemResponseDtoArrayList)
                    .build();

            foodCategoryDTOs.add(foodCategoryDto);
        }

        return foodCategoryDTOs;
    }

    @Override
    public void saveFoodCategoryByDto(FoodCategoryRequestDto foodCategoryRequestDto) {

        String foodCategoryName = foodCategoryRequestDto.getName().toLowerCase();

        Optional<FoodCategory> optionalFoodCategory =
                foodCategoryRepository.findFoodCategoryByName(foodCategoryName);

        FoodCategory foodCategory = optionalFoodCategory.orElse(
                FoodCategory
                        .builder()
                        .name(foodCategoryRequestDto.getName().toLowerCase())
                        .description(foodCategoryRequestDto.getDescription())
                        .foodItems(foodCategoryRequestDto.getFoodItems())
                        .build()
        );

        List<String> foodItemNamesInDataBase = new ArrayList<>();
        if (optionalFoodCategory.isPresent()) {
            List<FoodItem> foodItems =
                    foodItemRepository.findAllItemByCategoryId(optionalFoodCategory.get().getCategoryId());
            for (FoodItem foodItem : foodItems) {
                foodItemNamesInDataBase.add(foodItem.getName());
            }
        }

        List<FoodItem> foodItemList = foodCategoryRequestDto.getFoodItems();
        for (FoodItem foodItem : foodItemList) {

            foodItem.setAvailability(FoodStatus.AVAILABLE);
            foodItem.setFoodCategory(foodCategory);

            if (foodItemNamesInDataBase.contains(foodItem.getName())) {
                throw new FoodItemAlreadyExistException("Food item already exist");
            }
        }

        foodCategory.setFoodItems(foodItemList);
        foodCategoryRepository.save(foodCategory);
    }


}
