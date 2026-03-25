package com.foodapp.notificationservice.service.impl;

import com.foodapp.notificationservice.dto.NotificationRequest;
import com.foodapp.notificationservice.dto.NotificationResponse;
import com.foodapp.notificationservice.factory.NotificationFactory;
import com.foodapp.notificationservice.handler.NotificationHandler;
import com.foodapp.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationFactory factory;

    @Override
    public NotificationResponse send(NotificationRequest request) {
        NotificationHandler handler = factory.getHandler(request.getType());
        handler.send(request);
        return new NotificationResponse("SUCCESS", "Notification sent successfully");
    }
}