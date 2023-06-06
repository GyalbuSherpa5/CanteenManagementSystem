package com.don.solocanteenmanagementsystem.fooditem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreFoodQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quantityId;
    private int quantity;
    private Long customerId;
    private Long foodId;
    private String foodCategoryName;
    private long token;
}

