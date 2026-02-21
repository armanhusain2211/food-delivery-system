package com.foodapp.restaurantservice.repository;

import com.foodapp.restaurantservice.entity.RestaurantImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantImageRepository extends JpaRepository<RestaurantImage, Long> {
}