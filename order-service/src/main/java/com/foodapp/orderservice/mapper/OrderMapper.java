package com.foodapp.orderservice.mapper;

import com.foodapp.orderservice.dto.*;
import com.foodapp.orderservice.entity.*;

import java.util.List;

public class OrderMapper {

    public static Order toEntity(OrderRequest request) {

        Order order = Order.builder()
                .userId(request.getUserId())
                .restaurantId(request.getRestaurantId())
                .status(OrderStatus.CREATED)
                .build();

        List<OrderItem> items = request.getItems()
                .stream()
                .map(i -> OrderItem.builder()
                        .menuItemId(i.getMenuItemId())
                        .quantity(i.getQuantity())
                        .price(i.getPrice())
                        .order(order)
                        .build())
                .toList();

        order.setItems(items);

        double totalAmount = items.stream()
                .mapToDouble(i ->
                        (i.getPrice() == null ? 0 : i.getPrice()) *
                                (i.getQuantity() == null ? 0 : i.getQuantity())
                )
                .sum();

        order.setTotalAmount(totalAmount);

        return order;
    }

    public static OrderResponse toResponse(Order order) {

        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(i -> OrderItemResponse.builder()
                        .menuItemId(i.getMenuItemId())
                        .quantity(i.getQuantity())
                        .price(i.getPrice())
                        .build())
                .toList();

        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .restaurantId(order.getRestaurantId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .items(items)
                .build();
    }
}