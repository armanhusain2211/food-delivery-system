package com.foodapp.userservice.security;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void adminEndpoint_withoutToken_shouldReturnForbidden() {

        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "/api/users/admin/test",
                        String.class
                );

        assertEquals(HttpStatus.FORBIDDEN,
                response.getStatusCode());
    }
}