package com.foodapp.orderservice.controller;

import com.foodapp.orderservice.dto.OrderRequest;
import com.foodapp.orderservice.dto.OrderResponse;
import com.foodapp.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // CREATE ORDER
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest request) {

        OrderResponse response = orderService.createOrder(request);

        return ResponseEntity.ok(response);
    }

    // GET ALL ORDERS
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {

        List<OrderResponse> orders = orderService.getAllOrders();

        return ResponseEntity.ok(orders);
    }

    // GET ORDER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable Long id) {

        OrderResponse order = orderService.getOrderById(id);

        return ResponseEntity.ok(order);
    }

    // DELETE ORDER
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(
            @PathVariable Long id) {

        orderService.deleteOrder(id);

        return ResponseEntity.ok("Order deleted successfully");
    }

}