package com.foodapp.notificationservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.notificationservice.dto.NotificationRequest;
import com.foodapp.notificationservice.event.OrderEvent;
import com.foodapp.notificationservice.service.NotificationService;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class KafkaConsumerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final NotificationService notificationService = mock(NotificationService.class);

    private final OrderConsumer consumer =
            new OrderConsumer(objectMapper, notificationService);

    @Test
    void testConsume_success() throws Exception {

        OrderEvent event = new OrderEvent(
                "123",
                "test@gmail.com",
                "Order placed"
        );

        String json = objectMapper.writeValueAsString(event);

        consumer.consume(json);

        verify(notificationService, times(1)).send(any(NotificationRequest.class));
    }
}