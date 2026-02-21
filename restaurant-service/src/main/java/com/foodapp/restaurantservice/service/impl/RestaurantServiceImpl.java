package com.foodapp.restaurantservice.service.impl;

import com.foodapp.restaurantservice.dto.*;
import com.foodapp.restaurantservice.entity.*;
import com.foodapp.restaurantservice.exception.*;
import com.foodapp.restaurantservice.repository.*;
import com.foodapp.restaurantservice.service.RestaurantService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantResponse create(RestaurantRequest request) {

        if (restaurantRepository.existsByNameIgnoreCase(request.getName())) {
            throw new DuplicateRestaurantException("Restaurant already exists");
        }

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
            List<RestaurantImage> images = request.getImageUrls()
                    .stream()
                    .map(url -> RestaurantImage.builder()
                            .imageUrl(url)
                            .restaurant(restaurant)
                            .build())
                    .collect(Collectors.toList());

            restaurant.setImages(images);
        }

        Restaurant saved = restaurantRepository.save(restaurant);

        return mapToResponse(saved);
    }

    @Override
    public RestaurantResponse getById(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        return mapToResponse(restaurant);
    }

    @Override
    public Page<RestaurantSummaryResponse> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return restaurantRepository.findByActiveTrue(pageable)
                .map(this::mapToSummary);
    }

    @Override
    public Page<RestaurantSummaryResponse> getByCity(String city, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return restaurantRepository
                .findByAddress_CityIgnoreCaseAndActiveTrue(city, pageable)
                .map(this::mapToSummary);
    }

    @Override
    public RestaurantResponse update(Long id, RestaurantUpdateRequest request) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());

        restaurant.getAddress().setStreet(request.getStreet());
        restaurant.getAddress().setCity(request.getCity());
        restaurant.getAddress().setState(request.getState());
        restaurant.getAddress().setZipCode(request.getZipCode());

        Restaurant updated = restaurantRepository.save(restaurant);

        return mapToResponse(updated);
    }

    @Override
    public void delete(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        restaurant.setActive(false);

        restaurantRepository.save(restaurant);
    }

    private RestaurantResponse mapToResponse(Restaurant restaurant) {

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

    private RestaurantSummaryResponse mapToSummary(Restaurant restaurant) {

        return RestaurantSummaryResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .city(restaurant.getAddress().getCity())
                .active(restaurant.getActive())
                .build();
    }
}