package com.don.solocanteenmanagementsystem.fooditem.service;

import com.don.solocanteenmanagementsystem.category.exception.CategoryDoNotExistException;
import com.don.solocanteenmanagementsystem.fooditem.exception.FoodItemAlreadyExistException;
import com.don.solocanteenmanagementsystem.category.model.FoodCategory;
import com.don.solocanteenmanagementsystem.category.repository.FoodCategoryRepository;
import com.don.solocanteenmanagementsystem.fooditem.dto.FoodItemRequestDto;
import com.don.solocanteenmanagementsystem.fooditem.dto.FoodItemResponseDto;
import com.don.solocanteenmanagementsystem.fooditem.enums.FoodStatus;
import com.don.solocanteenmanagementsystem.fooditem.model.FoodItem;
import com.don.solocanteenmanagementsystem.fooditem.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    Logger logger = LoggerFactory.getLogger(FoodItemServiceImpl.class);

    @Override
    public void saveFoodItem(FoodItemRequestDto foodItemRequestDto) {
        String foodCategoryName = foodItemRequestDto.getFoodCategory().getName().toLowerCase();

        Optional<FoodCategory> optionalFoodCategory =
                foodCategoryRepository.findFoodCategoryByName(foodCategoryName);

        FoodCategory foodCategory = optionalFoodCategory
                .orElse(FoodCategory.builder()
                        .name(foodCategoryName)
                        .description(foodItemRequestDto.getFoodCategory().getDescription())
                        .build());

        List<String> foodItemNameInDataBase = new ArrayList<>();
        if (optionalFoodCategory.isPresent()) {
            List<FoodItem> foodItems =
                    foodItemRepository.findAllItemByCategoryId(optionalFoodCategory.get().getCategoryId());
            for (FoodItem foodItem : foodItems) {
                foodItemNameInDataBase.add(foodItem.getName());
            }
        }

        if (foodItemNameInDataBase.contains(foodItemRequestDto.getName())) {
            throw new FoodItemAlreadyExistException("This food item already exist");
        } else {
            FoodItem foodItem = FoodItem.builder()
                    .name(foodItemRequestDto.getName())
                    .description(foodItemRequestDto.getDescription())
                    .availability(FoodStatus.AVAILABLE)
                    .stock(foodItemRequestDto.getStock())
                    .foodCategory(foodCategory)
                    .price(foodItemRequestDto.getPrice())
                    .build();

            foodItemRepository.save(foodItem);
        }
    }

    @Override
    public List<FoodItemResponseDto> getAllFoodByCategoryName(String name) {

        Optional<FoodCategory> foodItem = foodCategoryRepository.findFoodCategoryByName(name);
        Long id;
        if (foodItem.isPresent()) {
            id = foodItem.get().getCategoryId();
        } else {
            throw new CategoryDoNotExistException("This category do not exist");
        }


        List<FoodItem> foodItem1 = foodItemRepository.findAllItemByCategoryId(id);

        List<FoodItemResponseDto> foodItemResponseDtoArrayList = new ArrayList<>();

        for (FoodItem item : foodItem1) {

            FoodItemResponseDto foodItemResponseDto = new FoodItemResponseDto();
            foodItemResponseDto.setName(item.getName());
            foodItemResponseDto.setAvailability(item.getAvailability());
            foodItemResponseDto.setPrice(item.getPrice());
            foodItemResponseDto.setStock(item.getStock());
            foodItemResponseDto.setDescription(item.getDescription());

            foodItemResponseDtoArrayList.add(foodItemResponseDto);
        }


        return foodItemResponseDtoArrayList;
    }

}
