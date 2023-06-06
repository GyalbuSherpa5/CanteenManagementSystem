package com.don.solocanteenmanagementsystem.order.dto;

import com.don.solocanteenmanagementsystem.fooditem.dto.FoodDtoForOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestOrderDto {

    private long customerId;
    private List<FoodDtoForOrder> foodItems = new ArrayList<>();

}
