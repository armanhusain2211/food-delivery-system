package com.foodapp.paymentservice.service;

import com.foodapp.paymentservice.client.OrderServiceClient;
import com.foodapp.paymentservice.dto.PaymentRequest;
import com.foodapp.paymentservice.dto.PaymentResponse;
import com.foodapp.paymentservice.entity.Payment;
import com.foodapp.paymentservice.repository.PaymentRepository;
import com.foodapp.paymentservice.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderServiceClient orderServiceClient;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void processPaymentTest() {

        PaymentRequest request = new PaymentRequest();
        request.setOrderId(1L);
        request.setUserId(1L);
        request.setAmount(new BigDecimal("450"));

        Payment payment = new Payment();
        payment.setId(1L);
        payment.setTransactionId("TXN123");

        Mockito.when(paymentRepository.save(any(Payment.class)))
                .thenReturn(payment);

        PaymentResponse response = paymentService.processPayment(request);

        assertNotNull(response);
    }
}