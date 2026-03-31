package com.foodapp.consumer;

import com.foodapp.dto.NotificationRequest;
import com.foodapp.event.OrderEvent;
import com.foodapp.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void consume(OrderEvent event) {

        NotificationRequest request = new NotificationRequest();
        request.setRecipient(event.getEmail());
        request.setMessage(event.getMessage());
        request.setType("EMAIL");

        notificationService.send(request);
    }
}