package com.foodapp.menuservice.dto;

import lombok.Data;

@Data
public class MenuRequest {
    private String restaurantId;
    private String name;
}