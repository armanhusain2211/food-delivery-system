package com.foodapp.menuservice.service.impl;

import com.foodapp.menuservice.client.RestaurantClient;
import com.foodapp.menuservice.dto.MenuRequest;
import com.foodapp.menuservice.dto.MenuResponse;
import com.foodapp.menuservice.dto.RestaurantResponse;
import com.foodapp.menuservice.entity.Menu;
import com.foodapp.menuservice.exception.MenuNotFoundException;
import com.foodapp.menuservice.mapper.MenuMapper;
import com.foodapp.menuservice.repository.MenuRepository;
import com.foodapp.menuservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantClient restaurantClient;

    @Override
    public MenuResponse createMenu(MenuRequest request) {
        Menu menu = MenuMapper.toEntity(request);
        return MenuMapper.toResponse(menuRepository.save(menu));
    }

    @Override
    public List<MenuResponse> getAllMenus() {

        return menuRepository.findAll()
                .stream()
                .map(menu -> {

                    RestaurantResponse restaurant = null;

                    if (menu.getRestaurantId() != null) {
                        restaurant =
                                restaurantClient.getRestaurantById(
                                        Long.valueOf(menu.getRestaurantId())
                                );
                    }

                    return MenuResponse.builder()
                            .id(menu.getId())
                            .name(menu.getName())
                            .restaurantId(menu.getRestaurantId())
                            .restaurant(restaurant)
                            .build();
                })
                .toList();
    }

    @Override
    public MenuResponse getMenu(Long id) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new MenuNotFoundException(
                                "Menu not found with id: " + id
                        )
                );

        RestaurantResponse restaurant = null;

        if (menu.getRestaurantId() != null) {
            restaurant =
                    restaurantClient.getRestaurantById(
                            Long.valueOf(menu.getRestaurantId())
                    );
        }

        return MenuResponse.builder()
                .id(menu.getId())
                .name(menu.getName())
                .restaurantId(menu.getRestaurantId())
                .restaurant(restaurant)
                .build();
    }
}