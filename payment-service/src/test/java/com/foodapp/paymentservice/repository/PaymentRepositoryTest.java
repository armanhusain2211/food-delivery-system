package com.foodapp.paymentservice.repository;

import com.foodapp.paymentservice.entity.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    void savePaymentTest() {

        Payment payment = new Payment();
        payment.setOrderId(101L);
        payment.setUserId(5L);
        payment.setAmount(new BigDecimal("450"));

        Payment saved = paymentRepository.save(payment);

        assertNotNull(saved.getId());
    }
}