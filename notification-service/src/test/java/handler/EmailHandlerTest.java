package com.foodapp.notificationservice.handler;

import com.foodapp.notificationservice.dto.NotificationRequest;
import com.foodapp.notificationservice.util.TemplateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmailHandlerTest {

    private final JavaMailSender mailSender = mock(JavaMailSender.class);
    private final TemplateUtil templateUtil = mock(TemplateUtil.class);

    private final EmailHandler handler =
            new EmailHandler(mailSender, templateUtil);

    @Test
    void testSend_success() {

        NotificationRequest request = new NotificationRequest();
        request.setRecipient("armanhusain2211@gmail.com");
        request.setMessage("Hello");

        when(templateUtil.loadTemplate(any())).thenReturn("Test ${message}");
        when(templateUtil.replace(any(), any(), any())).thenReturn("Test Hello");

        handler.send(request);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}