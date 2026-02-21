package com.foodapp.restaurantservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRequest {

    private String name;
    private String description;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private List<String> imageUrls;
}