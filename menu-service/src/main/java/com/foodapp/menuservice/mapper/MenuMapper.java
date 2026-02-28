package com.foodapp.menuservice.mapper;

import com.foodapp.menuservice.dto.MenuRequest;
import com.foodapp.menuservice.dto.MenuResponse;
import com.foodapp.menuservice.entity.Menu;

public class MenuMapper {

    public static Menu toEntity(MenuRequest request) {
        return Menu.builder()
                .restaurantId(request.getRestaurantId())
                .name(request.getName())
                .build();
    }

    public static MenuResponse toResponse(Menu menu) {
        return MenuResponse.builder()
                .id(menu.getId())
                .restaurantId(menu.getRestaurantId())
                .name(menu.getName())
                .build();
    }
}