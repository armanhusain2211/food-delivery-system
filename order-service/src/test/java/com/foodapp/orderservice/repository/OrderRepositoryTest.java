package com.foodapp.orderservice.repository;

import com.foodapp.orderservice.entity.Order;
import com.foodapp.orderservice.entity.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveOrderTest() {

        Order order = Order.builder()
                .userId(1L)
                .restaurantId(1L)
                .status(OrderStatus.CREATED)
                .totalAmount(200.0)
                .build();

        Order saved = orderRepository.save(order);

        assertNotNull(saved.getId());
    }

    @Test
    void findOrderByIdTest() {

        Order order = Order.builder()
                .userId(1L)
                .restaurantId(1L)
                .status(OrderStatus.CREATED)
                .totalAmount(200.0)
                .build();

        Order saved = orderRepository.save(order);

        Optional<Order> found = orderRepository.findById(saved.getId());

        assertTrue(found.isPresent());
    }

    @Test
    void findAllOrdersTest() {

        Order order1 = Order.builder()
                .userId(1L)
                .restaurantId(1L)
                .status(OrderStatus.CREATED)
                .totalAmount(200.0)
                .build();

        Order order2 = Order.builder()
                .userId(2L)
                .restaurantId(2L)
                .status(OrderStatus.CREATED)
                .totalAmount(300.0)
                .build();

        orderRepository.save(order1);
        orderRepository.save(order2);

        List<Order> orders = orderRepository.findAll();

        assertEquals(2, orders.size());
    }

    @Test
    void deleteOrderTest() {

        Order order = Order.builder()
                .userId(1L)
                .restaurantId(1L)
                .status(OrderStatus.CREATED)
                .totalAmount(200.0)
                .build();

        Order saved = orderRepository.save(order);

        orderRepository.deleteById(saved.getId());

        Optional<Order> result = orderRepository.findById(saved.getId());

        assertTrue(result.isEmpty());
    }
}