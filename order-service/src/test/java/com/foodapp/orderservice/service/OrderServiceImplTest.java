package com.foodapp.orderservice.service.impl;

import com.foodapp.orderservice.dto.OrderRequest;
import com.foodapp.orderservice.dto.OrderResponse;
import com.foodapp.orderservice.entity.Order;
import com.foodapp.orderservice.entity.OrderStatus;
import com.foodapp.orderservice.exception.OrderNotFoundException;
import com.foodapp.orderservice.mapper.OrderMapper;
import com.foodapp.orderservice.repository.OrderRepository;
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

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void createOrderTest() {

        OrderRequest request = OrderRequest.builder()
                .userId(1L)
                .restaurantId(1L)
                .build();

        Order order = Order.builder()
                .id(1L)
                .userId(1L)
                .restaurantId(1L)
                .status(OrderStatus.CREATED)
                .totalAmount(200.0)
                .build();

        OrderResponse response = OrderResponse.builder()
                .id(1L)
                .userId(1L)
                .restaurantId(1L)
                .totalAmount(200.0)
                .build();

        try (MockedStatic<OrderMapper> mapper = mockStatic(OrderMapper.class)) {

            mapper.when(() -> OrderMapper.toEntity(request)).thenReturn(order);
            when(orderRepository.save(order)).thenReturn(order);
            mapper.when(() -> OrderMapper.toResponse(order)).thenReturn(response);

            OrderResponse result = orderService.createOrder(request);

            assertNotNull(result);
            assertEquals(1L, result.getId());
        }
    }

    @Test
    void getAllOrdersTest() {

        Order order = Order.builder()
                .id(1L)
                .userId(1L)
                .restaurantId(1L)
                .status(OrderStatus.CREATED)
                .totalAmount(200.0)
                .build();

        when(orderRepository.findAll()).thenReturn(List.of(order));

        try (MockedStatic<OrderMapper> mapper = mockStatic(OrderMapper.class)) {

            OrderResponse response = OrderResponse.builder()
                    .id(1L)
                    .userId(1L)
                    .restaurantId(1L)
                    .totalAmount(200.0)
                    .build();

            mapper.when(() -> OrderMapper.toResponse(order)).thenReturn(response);

            List<OrderResponse> result = orderService.getAllOrders();

            assertNotNull(result);
            assertEquals(1, result.size());
        }
    }

    @Test
    void getOrderByIdTest() {

        Order order = Order.builder()
                .id(1L)
                .userId(1L)
                .restaurantId(1L)
                .status(OrderStatus.CREATED)
                .totalAmount(200.0)
                .build();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        try (MockedStatic<OrderMapper> mapper = mockStatic(OrderMapper.class)) {

            OrderResponse response = OrderResponse.builder()
                    .id(1L)
                    .userId(1L)
                    .restaurantId(1L)
                    .totalAmount(200.0)
                    .build();

            mapper.when(() -> OrderMapper.toResponse(order)).thenReturn(response);

            OrderResponse result = orderService.getOrderById(1L);

            assertNotNull(result);
            assertEquals(1L, result.getId());
        }
    }

    @Test
    void getOrderNotFoundTest() {

        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class,
                () -> orderService.getOrderById(1L));
    }
}