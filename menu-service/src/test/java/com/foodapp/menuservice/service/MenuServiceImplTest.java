package com.foodapp.menuservice.service.impl;

import com.foodapp.menuservice.client.RestaurantClient;
import com.foodapp.menuservice.dto.MenuRequest;
import com.foodapp.menuservice.dto.MenuResponse;
import com.foodapp.menuservice.dto.RestaurantResponse;
import com.foodapp.menuservice.entity.Menu;
import com.foodapp.menuservice.exception.MenuNotFoundException;
import com.foodapp.menuservice.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.foodapp.menuservice.mapper.MenuMapper;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private RestaurantClient restaurantClient;

    @InjectMocks
    private MenuServiceImpl menuService;

    @Test
    void createMenuTest() {
        MenuRequest request = new MenuRequest();
        request.setName("Lunch Menu");
        request.setRestaurantId("1");

        Menu menu = Menu.builder().id(1L).name("Lunch Menu").restaurantId("1").build();
        Menu savedMenu = Menu.builder().id(1L).name("Lunch Menu").restaurantId("1").build();

        MenuResponse response = MenuResponse.builder().id(1L).name("Lunch Menu").restaurantId("1").build();

        try (MockedStatic<MenuMapper> mapper = mockStatic(MenuMapper.class)) {
            mapper.when(() -> MenuMapper.toEntity(request)).thenReturn(menu);
            when(menuRepository.save(menu)).thenReturn(savedMenu);
            mapper.when(() -> MenuMapper.toResponse(savedMenu)).thenReturn(response);

            MenuResponse result = menuService.createMenu(request);

            assertNotNull(result);
            assertEquals("Lunch Menu", result.getName());
            verify(menuRepository, times(1)).save(menu);
        }
    }

    @Test
    void getAllMenusTest() {
        Menu menu = Menu.builder().id(1L).name("Dinner Menu").restaurantId("1").build();

        RestaurantResponse restaurant = RestaurantResponse.builder().id(1L).name("Test Restaurant").build();

        when(menuRepository.findAll()).thenReturn(List.of(menu));
        when(restaurantClient.getRestaurantById(1L)).thenReturn(restaurant);

        List<MenuResponse> result = menuService.getAllMenus();

        assertEquals(1, result.size());
        assertEquals("Dinner Menu", result.get(0).getName());
        assertNotNull(result.get(0).getRestaurant());
    }

    @Test
    void getMenuTest() {
        Menu menu = Menu.builder().id(1L).name("Breakfast Menu").restaurantId("1").build();

        RestaurantResponse restaurant = RestaurantResponse.builder().id(1L).name("Cafe").build();

        when(menuRepository.findById(1L)).thenReturn(Optional.of(menu));
        when(restaurantClient.getRestaurantById(1L)).thenReturn(restaurant);

        MenuResponse result = menuService.getMenu(1L);

        assertNotNull(result);
        assertEquals("Breakfast Menu", result.getName());
        assertNotNull(result.getRestaurant());
    }

    @Test
    void getMenuNotFoundTest() {
        when(menuRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MenuNotFoundException.class, () -> menuService.getMenu(1L));
    }
}