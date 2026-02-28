package com.foodapp.menuservice.dto;

import lombok.Data;

@Data
public class MenuItemRequest {
    private String name;
    private Double price;
    private Boolean available;
    private Long categoryId;
}