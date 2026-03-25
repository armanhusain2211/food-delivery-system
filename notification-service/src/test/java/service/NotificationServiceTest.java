package com.foodapp.notificationservice.service;

import com.foodapp.notificationservice.dto.NotificationRequest;
import com.foodapp.notificationservice.dto.NotificationResponse;
import com.foodapp.notificationservice.factory.NotificationFactory;
import com.foodapp.notificationservice.handler.NotificationHandler;
import com.foodapp.notificationservice.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    private final NotificationFactory factory = mock(NotificationFactory.class);
    private final NotificationHandler handler = mock(NotificationHandler.class);

    private final NotificationServiceImpl service =
            new NotificationServiceImpl(factory);

    @Test
    void testSend_success() {

        NotificationRequest request = new NotificationRequest();
        request.setType("EMAIL");

        when(factory.getHandler("EMAIL")).thenReturn(handler);

        NotificationResponse response = service.send(request);

        verify(handler, times(1)).send(request);
        assertEquals("SUCCESS", response.getStatus());
    }
}