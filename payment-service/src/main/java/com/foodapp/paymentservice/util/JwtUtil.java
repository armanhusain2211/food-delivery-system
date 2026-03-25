package com.foodapp.paymentservice.util;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String extractUsername(String token) {
        return "test-user";
    }

    public boolean validateToken(String token) {
        return true;
    }
}