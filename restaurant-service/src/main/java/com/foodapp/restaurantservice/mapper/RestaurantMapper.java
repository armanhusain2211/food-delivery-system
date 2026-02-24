package com.foodapp.restaurantservice.mapper;

import com.foodapp.restaurantservice.dto.RestaurantRequest;
import com.foodapp.restaurantservice.dto.RestaurantResponse;
import com.foodapp.restaurantservice.dto.RestaurantSummaryResponse;
import com.foodapp.restaurantservice.entity.Restaurant;
import com.foodapp.restaurantservice.entity.RestaurantAddress;
import com.foodapp.restaurantservice.entity.RestaurantImage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {

    public Restaurant toEntity(RestaurantRequest request) {

        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .description(request.getDescription())
                .active(true)
                .build();

        RestaurantAddress address = RestaurantAddress.builder()
                .street(request.getStreet())
                .city(request.getCity())
                .state(request.getState())
                .zipCode(request.getZipCode())
                .restaurant(restaurant)
                .build();

        restaurant.setAddress(address);

        if (request.getImageUrls() != null) {
            List<RestaurantImage> images =
                    request.getImageUrls()
                            .stream()
                            .map(url ->
                                    RestaurantImage.builder()
                                            .imageUrl(url)
                                            .restaurant(restaurant)
                                            .build())
                            .collect(Collectors.toList());

            restaurant.setImages(images);
        }

        return restaurant;
    }

    public RestaurantResponse toResponse(Restaurant restaurant) {

        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .active(restaurant.getActive())
                .street(restaurant.getAddress().getStreet())
                .city(restaurant.getAddress().getCity())
                .state(restaurant.getAddress().getState())
                .zipCode(restaurant.getAddress().getZipCode())
                .imageUrls(
                        restaurant.getImages() == null ? null :
                                restaurant.getImages()
                                        .stream()
                                        .map(RestaurantImage::getImageUrl)
                                        .toList()
                )
                .build();
    }

    public RestaurantSummaryResponse toSummary(Restaurant restaurant) {

        return RestaurantSummaryResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .city(restaurant.getAddress().getCity())
                .active(restaurant.getActive())
                .build();
    }
}