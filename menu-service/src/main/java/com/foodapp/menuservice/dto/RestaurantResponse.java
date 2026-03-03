package com.foodapp.menuservice.dto;

import lombok.Data;
import java.util.List;

@Data
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