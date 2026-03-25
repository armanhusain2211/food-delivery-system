package com.foodapp.notificationservice.service;

import com.foodapp.notificationservice.dto.NotificationRequest;
import com.foodapp.notificationservice.dto.NotificationResponse;

public interface NotificationService {
    NotificationResponse send(NotificationRequest request);
}