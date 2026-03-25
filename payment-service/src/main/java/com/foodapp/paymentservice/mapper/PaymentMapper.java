package com.foodapp.paymentservice.mapper;

import com.foodapp.paymentservice.dto.PaymentRequest;
import com.foodapp.paymentservice.entity.Payment;
import com.foodapp.paymentservice.enums.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentMapper {

    public static Payment mapToEntity(PaymentRequest request, String transactionId) {

        return Payment.builder()
                .orderId(request.getOrderId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .paymentStatus(PaymentStatus.SUCCESS)
                .transactionId(transactionId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}