package com.foodapp.handler;

import com.foodapp.dto.NotificationRequest;
import com.foodapp.util.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailHandler implements NotificationHandler {

    private final JavaMailSender mailSender;
    private final TemplateUtil templateUtil;

    @Override
    public void send(NotificationRequest request) {
        String template = templateUtil.loadTemplate("email-template.html");
        String content = templateUtil.replace(template, "message", request.getMessage());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getRecipient());
        message.setSubject("Notification");
        message.setText(content);
        mailSender.send(message);
    }
}