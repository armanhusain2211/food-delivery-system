package com.foodapp.notificationservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.notificationservice.dto.NotificationRequest;
import com.foodapp.notificationservice.event.OrderEvent;
import com.foodapp.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void consume(String message) {
        try {
            OrderEvent event = objectMapper.readValue(message, OrderEvent.class);

            NotificationRequest request = new NotificationRequest();
            request.setType("EMAIL");
            request.setRecipient(event.getUserEmail());
            request.setMessage(event.getMessage());

            notificationService.send(request);

        } catch (Exception e) {
            throw new RuntimeException("Error processing Kafka message", e);
        }
    }
}