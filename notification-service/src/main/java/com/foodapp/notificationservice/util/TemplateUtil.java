package com.foodapp.notificationservice.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class TemplateUtil {

    public String loadTemplate(String fileName) {
        try {
            ClassPathResource resource = new ClassPathResource("templates/" + fileName);
            Path path = resource.getFile().toPath();
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load template");
        }
    }

    public String replace(String template, String key, String value) {
        return template.replace("${" + key + "}", value);
    }
}