package com.foodapp.restaurantservice.repository;

import com.foodapp.restaurantservice.entity.RestaurantAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantAddressRepository extends JpaRepository<RestaurantAddress, Long> {
}