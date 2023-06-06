package com.don.solocanteenmanagementsystem.fooditem.model;

import com.don.solocanteenmanagementsystem.category.model.FoodCategory;
import com.don.solocanteenmanagementsystem.fooditem.enums.FoodStatus;
import com.don.solocanteenmanagementsystem.order.model.Order;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;
    private String name;
    private String description;
    private double price;
    private int stock;

    @Enumerated(EnumType.STRING)
    private FoodStatus availability;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;

    @ManyToMany(mappedBy = "foodItems")
    private List<Order> orders = new ArrayList<>();

}
