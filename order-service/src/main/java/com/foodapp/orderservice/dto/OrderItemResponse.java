package com.foodapp.orderservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponse {

    private Long menuItemId;
    private Integer quantity;
    private Double price;
}