package com.foodapp.orderservice.dto;

import com.foodapp.orderservice.entity.OrderStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long id;
    private Long userId;
    private Long restaurantId;
    private Double totalAmount;
    private OrderStatus status;
    private List<OrderItemResponse> items;
}