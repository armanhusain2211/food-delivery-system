package com.foodapp.handler;

import com.foodapp.dto.NotificationRequest;
import org.springframework.stereotype.Component;

@Component
public class PushHandler implements NotificationHandler {

    @Override
    public void send(NotificationRequest request) {
        System.out.println("Push notification sent to " + request.getRecipient() + " : " + request.getMessage());
    }
}