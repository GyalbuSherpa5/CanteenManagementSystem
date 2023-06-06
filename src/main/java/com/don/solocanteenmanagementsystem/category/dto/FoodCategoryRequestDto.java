package com.don.solocanteenmanagementsystem.category.dto;

import com.don.solocanteenmanagementsystem.fooditem.model.FoodItem;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FoodCategoryRequestDto {
    private String name;
    private String description;
    private List<FoodItem> foodItems = new ArrayList<>();
}
