package com.foodapp.restaurantservice.service;

import com.foodapp.restaurantservice.dto.*;

import org.springframework.data.domain.Page;

public interface RestaurantService {

    RestaurantResponse create(RestaurantRequest request);

    RestaurantResponse getById(Long id);

    Page<RestaurantSummaryResponse> getAll(int page, int size);

    Page<RestaurantSummaryResponse> getByCity(String city, int page, int size);

    RestaurantResponse update(Long id, RestaurantUpdateRequest request);

    void delete(Long id);
}