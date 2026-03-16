package com.foodapp.menuservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.menuservice.dto.MenuRequest;
import com.foodapp.menuservice.dto.MenuResponse;
import com.foodapp.menuservice.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MenuController.class)
@AutoConfigureMockMvc(addFilters = false)
class MenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MenuService menuService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getMenusTest() throws Exception {

        MenuResponse response = MenuResponse.builder()
                .id(1L)
                .name("Dinner Menu")
                .restaurantId("1")
                .build();

        when(menuService.getAllMenus()).thenReturn(List.of(response));

        mockMvc.perform(get("/menus"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dinner Menu"));
    }

    @Test
    void createMenuTest() throws Exception {

        MenuRequest request = new MenuRequest();
        request.setName("Lunch Menu");
        request.setRestaurantId("1");

        MenuResponse response = MenuResponse.builder()
                .id(1L)
                .name("Lunch Menu")
                .restaurantId("1")
                .build();

        when(menuService.createMenu(request)).thenReturn(response);

        mockMvc.perform(post("/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lunch Menu"));
    }

    @Test
    void getMenuTest() throws Exception {

        MenuResponse response = MenuResponse.builder()
                .id(1L)
                .name("Breakfast Menu")
                .restaurantId("1")
                .build();

        when(menuService.getMenu(1L)).thenReturn(response);

        mockMvc.perform(get("/menus/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Breakfast Menu"));
    }
}