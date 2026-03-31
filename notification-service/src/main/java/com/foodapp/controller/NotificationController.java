package com.foodapp.controller;

import com.foodapp.dto.NotificationRequest;
import com.foodapp.dto.NotificationResponse;
import com.foodapp.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public NotificationResponse sendNotification(@RequestBody NotificationRequest request) {
        return notificationService.send(request);
    }
}