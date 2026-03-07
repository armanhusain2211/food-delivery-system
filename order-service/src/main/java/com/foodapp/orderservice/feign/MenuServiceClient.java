package com.foodapp.orderservice.feign;

import com.foodapp.orderservice.dto.OrderItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "menu-service")
public interface MenuServiceClient {

    @GetMapping("/menu-items/{id}")
    OrderItemResponse getMenuItemById(@PathVariable Long id);
}