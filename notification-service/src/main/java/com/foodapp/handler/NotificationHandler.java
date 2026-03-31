package com.foodapp.handler;

import com.foodapp.dto.NotificationRequest;

public interface NotificationHandler {
    void send(NotificationRequest request);
}