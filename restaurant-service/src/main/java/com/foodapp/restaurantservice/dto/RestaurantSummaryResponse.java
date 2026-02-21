package com.foodapp.restaurantservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantSummaryResponse {

    private Long id;
    private String name;
    private String city;
    private Boolean active;
}