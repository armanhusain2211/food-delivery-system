package com.foodapp.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @PostMapping("/orders/{orderId}/payment-success")
    void updatePaymentStatus(@PathVariable("orderId") Long orderId);
}