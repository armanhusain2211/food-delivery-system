package com.foodapp.notificationservice.controller;

import com.foodapp.notificationservice.dto.NotificationRequest;
import com.foodapp.notificationservice.dto.NotificationResponse;
import com.foodapp.notificationservice.service.NotificationService;
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