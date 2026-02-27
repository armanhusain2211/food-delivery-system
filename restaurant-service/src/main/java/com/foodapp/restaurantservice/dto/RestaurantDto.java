package com.foodapp.restaurantservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class RestaurantDto {

    private Long id;
    private String name;
    private String description;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private List<String> imageUrls;
}