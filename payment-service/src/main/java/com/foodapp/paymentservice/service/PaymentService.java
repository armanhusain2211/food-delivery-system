package com.foodapp.paymentservice.service;

import com.foodapp.paymentservice.dto.PaymentRequest;
import com.foodapp.paymentservice.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse processPayment(PaymentRequest request);
}