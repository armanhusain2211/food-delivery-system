package com.foodapp.paymentservice.service.impl;

import com.foodapp.paymentservice.client.OrderServiceClient;
import com.foodapp.paymentservice.dto.PaymentRequest;
import com.foodapp.paymentservice.dto.PaymentResponse;
import com.foodapp.paymentservice.entity.Payment;
import com.foodapp.paymentservice.mapper.PaymentMapper;
import com.foodapp.paymentservice.repository.PaymentRepository;
import com.foodapp.paymentservice.service.PaymentService;
import com.foodapp.paymentservice.util.TransactionIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderServiceClient orderServiceClient;

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {

        String transactionId = TransactionIdGenerator.generate();

        Payment payment = PaymentMapper.mapToEntity(request, transactionId);

        Payment saved = paymentRepository.save(payment);

        try {
            orderServiceClient.updatePaymentStatus(saved.getOrderId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return PaymentResponse.builder()
                .paymentId(saved.getId())
                .transactionId(saved.getTransactionId())
                .paymentStatus(saved.getPaymentStatus())
                .build();
    }
}