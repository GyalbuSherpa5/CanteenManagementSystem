package com.don.solocanteenmanagementsystem.order.model;

import com.don.solocanteenmanagementsystem.customer.model.Customer;
import com.don.solocanteenmanagementsystem.fooditem.model.FoodItem;
import com.don.solocanteenmanagementsystem.fooditem.model.StoreFoodQuantity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private double totalPrice;
    private double quantity;
    @CreationTimestamp
    private LocalDateTime orderedDate;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "order_food",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")

    )
    private List<FoodItem> foodItems = new ArrayList<>();

}
