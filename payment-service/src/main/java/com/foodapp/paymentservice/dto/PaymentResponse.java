package com.foodapp.paymentservice.dto;

import com.foodapp.paymentservice.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {

    private Long paymentId;
    private String transactionId;
    private PaymentStatus paymentStatus;
}