package com.foodapp.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.orderservice.dto.OrderRequest;
import com.foodapp.orderservice.dto.OrderResponse;
import com.foodapp.orderservice.security.JwtFilter;
import com.foodapp.orderservice.security.JwtUtil;
import com.foodapp.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @MockitoBean
    private JwtFilter jwtFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createOrderTest() throws Exception {

        OrderRequest request = OrderRequest.builder()
                .userId(1L)
                .restaurantId(1L)
                .build();

        OrderResponse response = OrderResponse.builder()
                .id(1L)
                .userId(1L)
                .restaurantId(1L)
                .totalAmount(200.0)
                .build();

        when(orderService.createOrder(any(OrderRequest.class))).thenReturn(response);

        mockMvc.perform(post("/orders")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllOrdersTest() throws Exception {

        OrderResponse response = OrderResponse.builder()
                .id(1L)
                .userId(1L)
                .restaurantId(1L)
                .totalAmount(200.0)
                .build();

        when(orderService.getAllOrders()).thenReturn(List.of(response));

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderByIdTest() throws Exception {

        OrderResponse response = OrderResponse.builder()
                .id(1L)
                .userId(1L)
                .restaurantId(1L)
                .totalAmount(200.0)
                .build();

        when(orderService.getOrderById(1L)).thenReturn(response);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOrderTest() throws Exception {

        mockMvc.perform(delete("/orders/1")
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}