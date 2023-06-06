package com.don.solocanteenmanagementsystem.order.controller;

import com.don.solocanteenmanagementsystem.order.dto.RequestOrderDto;
import com.don.solocanteenmanagementsystem.order.dto.ResponseOrderDto;
import com.don.solocanteenmanagementsystem.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public String placeOrder(@RequestBody RequestOrderDto requestOrderDto) {
        orderService.placeOrder(requestOrderDto);
        return "Your order is placed successfully";
    }

    @GetMapping("/orders")
    public List<ResponseOrderDto> fetchAllOrder() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseOrderDto fetchOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }


}
