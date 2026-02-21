package com.foodapp.restaurantservice.repository;

import com.foodapp.restaurantservice.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    boolean existsByNameIgnoreCase(String name);

    Page<Restaurant> findByActiveTrue(Pageable pageable);

    Page<Restaurant> findByAddress_CityIgnoreCaseAndActiveTrue(String city, Pageable pageable);
}