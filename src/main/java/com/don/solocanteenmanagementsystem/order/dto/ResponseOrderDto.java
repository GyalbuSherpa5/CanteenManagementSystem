package com.don.solocanteenmanagementsystem.order.dto;

import com.don.solocanteenmanagementsystem.fooditem.dto.FoodDtoForOrder;
import com.don.solocanteenmanagementsystem.fooditem.dto.FoodDtoForResponseOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDto {
    private double totalPrice;
    private double quantity;
    private long customerId;
    private List<FoodDtoForResponseOrder> foodItems;
}
