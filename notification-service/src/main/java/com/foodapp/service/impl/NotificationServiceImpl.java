package com.foodapp.service.impl;

import com.foodapp.dto.NotificationRequest;
import com.foodapp.dto.NotificationResponse;
import com.foodapp.factory.NotificationFactory;
import com.foodapp.handler.NotificationHandler;
import com.foodapp.service.NotificationService;
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