package com.foodapp.apigateway.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationFilterTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnUnauthorizedWhenTokenMissing() {

        webTestClient.get()
                .uri("http://localhost:" + port + "/api/users/test")
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    void shouldAllowRequestWhenTokenPresent() {

        webTestClient.get()
                .uri("http://localhost:" + port + "/api/users/test")
                .header("Authorization", "Bearer test-token")
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }
}