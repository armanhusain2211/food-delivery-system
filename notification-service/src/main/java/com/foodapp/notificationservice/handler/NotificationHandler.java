package com.foodapp.notificationservice.handler;

import com.foodapp.notificationservice.dto.NotificationRequest;

public interface NotificationHandler {
    void send(NotificationRequest request);
}