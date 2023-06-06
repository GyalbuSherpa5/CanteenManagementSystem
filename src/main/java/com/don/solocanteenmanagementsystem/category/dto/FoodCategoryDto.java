package com.don.solocanteenmanagementsystem.category.dto;

import com.don.solocanteenmanagementsystem.fooditem.dto.FoodItemResponseDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FoodCategoryDto {
    private String name;
    private String description;
    private List<FoodItemResponseDto> foodItems;
}
