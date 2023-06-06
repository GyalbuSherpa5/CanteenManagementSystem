package com.don.solocanteenmanagementsystem.order.service;

import com.don.solocanteenmanagementsystem.order.dto.RequestOrderDto;
import com.don.solocanteenmanagementsystem.order.dto.ResponseOrderDto;

import java.util.List;

public interface OrderService {
    void placeOrder(RequestOrderDto requestOrderDto);

    List<ResponseOrderDto> getAllOrders();

    ResponseOrderDto getOrderById(Long id);
}
