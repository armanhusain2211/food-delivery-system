package com.foodapp.orderservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Configuration
@EnableFeignClients(basePackages = "com.foodapp.orderservice.feign")
public class AppConfig {
}