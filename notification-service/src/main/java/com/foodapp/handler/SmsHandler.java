package com.foodapp.handler;

import com.foodapp.dto.NotificationRequest;
import org.springframework.stereotype.Component;

@Component
public class SmsHandler implements NotificationHandler {

    @Override
    public void send(NotificationRequest request) {
        System.out.println("SMS sent to " + request.getRecipient() + " : " + request.getMessage());
    }
}