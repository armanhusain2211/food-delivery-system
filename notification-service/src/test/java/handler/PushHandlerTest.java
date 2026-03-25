package com.foodapp.notificationservice.handler;

import com.foodapp.notificationservice.dto.NotificationRequest;
import org.junit.jupiter.api.Test;

class PushHandlerTest {

    private final PushHandler handler = new PushHandler();

    @Test
    void testSend() {
        NotificationRequest request = new NotificationRequest();
        request.setRecipient("user1");
        request.setMessage("Test Push");

        handler.send(request);
    }
}