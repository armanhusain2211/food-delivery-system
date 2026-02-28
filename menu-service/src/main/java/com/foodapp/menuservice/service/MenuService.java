package com.foodapp.menuservice.service;

import com.foodapp.menuservice.dto.MenuRequest;
import com.foodapp.menuservice.dto.MenuResponse;

import java.util.List;

public interface MenuService {

    MenuResponse createMenu(MenuRequest request);

    List<MenuResponse> getAllMenus();

    MenuResponse getMenu(Long id);
}