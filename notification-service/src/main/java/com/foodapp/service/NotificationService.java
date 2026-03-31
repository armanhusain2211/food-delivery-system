package com.foodapp.service;

import com.foodapp.dto.NotificationRequest;
import com.foodapp.dto.NotificationResponse;

public interface NotificationService {
    NotificationResponse send(NotificationRequest request);
}