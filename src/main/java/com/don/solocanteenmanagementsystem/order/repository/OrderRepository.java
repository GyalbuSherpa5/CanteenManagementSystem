package com.don.solocanteenmanagementsystem.order.repository;

import com.don.solocanteenmanagementsystem.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
