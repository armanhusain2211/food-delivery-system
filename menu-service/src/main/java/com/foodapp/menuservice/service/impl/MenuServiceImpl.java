package com.foodapp.menuservice.service.impl;

import com.foodapp.menuservice.dto.MenuRequest;
import com.foodapp.menuservice.dto.MenuResponse;
import com.foodapp.menuservice.entity.Menu;
import com.foodapp.menuservice.exception.MenuNotFoundException;
import com.foodapp.menuservice.mapper.MenuMapper;
import com.foodapp.menuservice.repository.MenuRepository;
import com.foodapp.menuservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public MenuResponse createMenu(MenuRequest request) {
        Menu menu = MenuMapper.toEntity(request);
        return MenuMapper.toResponse(menuRepository.save(menu));
    }

    @Override
    public List<MenuResponse> getAllMenus() {
        return menuRepository.findAll()
                .stream()
                .map(MenuMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MenuResponse getMenu(Long id) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() ->
                        new MenuNotFoundException("Menu not found with id: " + id)
                );

        return MenuMapper.toResponse(menu);
    }
}