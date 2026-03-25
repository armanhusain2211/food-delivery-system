package com.foodapp.notificationservice.handler;

import com.foodapp.notificationservice.dto.NotificationRequest;
import org.springframework.stereotype.Component;

@Component
public class SmsHandler implements NotificationHandler {

    @Override
    public void send(NotificationRequest request) {
        System.out.println("SMS sent to " + request.getRecipient() + " : " + request.getMessage());
    }
}