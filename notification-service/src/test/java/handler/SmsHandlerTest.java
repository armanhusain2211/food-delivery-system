package com.foodapp.notificationservice.handler;

import com.foodapp.notificationservice.dto.NotificationRequest;
import org.junit.jupiter.api.Test;

class SmsHandlerTest {

    private final SmsHandler handler = new SmsHandler();

    @Test
    void testSend() {
        NotificationRequest request = new NotificationRequest();
        request.setRecipient("9999999999");
        request.setMessage("Test SMS");

        handler.send(request);
    }
}