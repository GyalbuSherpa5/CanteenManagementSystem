package com.don.solocanteenmanagementsystem.category.model;

import com.don.solocanteenmanagementsystem.fooditem.model.FoodItem;
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
public class FoodCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "foodCategory")
    private List<FoodItem> foodItems = new ArrayList<>();
}
