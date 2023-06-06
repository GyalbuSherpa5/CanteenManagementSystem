package com.don.solocanteenmanagementsystem.fooditem.dto;

import com.don.solocanteenmanagementsystem.fooditem.enums.FoodStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FoodItemResponseDto {
    private String name;
    private String description;
    private double price;
    private int stock;
    private FoodStatus availability;

}
