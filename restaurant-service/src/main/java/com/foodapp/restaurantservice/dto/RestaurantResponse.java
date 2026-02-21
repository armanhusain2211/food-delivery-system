package com.foodapp.restaurantservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponse {

    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private List<String> imageUrls;
}