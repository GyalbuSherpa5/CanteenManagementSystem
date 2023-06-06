package com.don.solocanteenmanagementsystem.fooditem.dto;

import com.don.solocanteenmanagementsystem.category.model.FoodCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodItemRequestDto {
    private String name;
    private String description;
    private double price;
    private int stock;
    private FoodCategory foodCategory;

}
