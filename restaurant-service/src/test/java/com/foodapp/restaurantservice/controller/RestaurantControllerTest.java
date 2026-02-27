package com.foodapp.restaurantservice.controller;

import com.foodapp.restaurantservice.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {

    @Mock
    private RestaurantService service;

    @InjectMocks
    private RestaurantController controller;

    @Test
    void testGetAll() throws Exception {

        MockMvc mockMvc =
                MockMvcBuilders.standaloneSetup(controller)
                        .build();

        mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk());
    }
}