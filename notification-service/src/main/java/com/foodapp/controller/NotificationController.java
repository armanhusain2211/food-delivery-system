package com.foodapp.controller;

import com.foodapp.dto.NotificationRequest;
import com.foodapp.dto.NotificationResponse;
import com.foodapp.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse> sendNotification(
            @Valid @RequestBody NotificationRequest request) {

        log.info("Received notification request: {}", request);

        NotificationResponse response = notificationService.send(request);

        log.info("Notification sent successfully");

        return ResponseEntity.ok(response);
    }
}