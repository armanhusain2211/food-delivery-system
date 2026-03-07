package com.foodapp.orderservice.service;

import com.foodapp.orderservice.dto.OrderRequest;
import com.foodapp.orderservice.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    void deleteOrder(Long id);
}