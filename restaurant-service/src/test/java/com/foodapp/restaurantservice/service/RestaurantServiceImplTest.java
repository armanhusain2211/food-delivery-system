package com.foodapp.restaurantservice.service;

import com.foodapp.restaurantservice.dto.RestaurantResponse;
import com.foodapp.restaurantservice.entity.Restaurant;
import com.foodapp.restaurantservice.entity.RestaurantAddress;
import com.foodapp.restaurantservice.repository.RestaurantRepository;
import com.foodapp.restaurantservice.service.impl.RestaurantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceImplTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private RestaurantServiceImpl service;

    @Test
    void testGetById() {

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test");

        RestaurantAddress address = new RestaurantAddress();
        address.setStreet("Street");
        address.setCity("City");
        address.setState("State");
        address.setZipCode("12345");

        restaurant.setAddress(address);

        when(repository.findById(1L))
                .thenReturn(Optional.of(restaurant));

        RestaurantResponse response =
                service.getById(1L);

        assertNotNull(response);
        assertEquals("Test", response.getName());
    }

    @Test
    void testDelete() {

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(restaurant));

        service.delete(1L);

        verify(repository).save(any(Restaurant.class));
    }
}