package com.foodapp.menuservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuResponse {
    private Long id;
    private String restaurantId;
    private String name;
}