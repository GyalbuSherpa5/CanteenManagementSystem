package com.don.solocanteenmanagementsystem.fooditem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodDtoForResponseOrder {
    private String foodName;
    private long quantity;
}
