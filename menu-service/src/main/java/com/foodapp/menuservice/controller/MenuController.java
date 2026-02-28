package com.foodapp.menuservice.controller;

import com.foodapp.menuservice.dto.MenuRequest;
import com.foodapp.menuservice.dto.MenuResponse;
import com.foodapp.menuservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public MenuResponse createMenu(@RequestBody MenuRequest request) {
        return menuService.createMenu(request);
    }

    @GetMapping
    public List<MenuResponse> getMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/{id}")
    public MenuResponse getMenu(@PathVariable Long id) {
        return menuService.getMenu(id);
    }
}