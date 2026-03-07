package com.foodapp.orderservice.security;

import com.foodapp.orderservice.dto.OrderResponse;
import com.foodapp.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @MockitoBean
    private OrderService orderService;

    @Test
    void shouldReturn401WithoutToken() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldAllowAccessWithValidToken() throws Exception {

        String token = jwtUtil.generateToken("user");

        OrderResponse response = OrderResponse.builder()
                .id(1L)
                .userId(1L)
                .restaurantId(1L)
                .totalAmount(200.0)
                .build();

        when(orderService.getAllOrders()).thenReturn(List.of(response));

        mockMvc.perform(get("/orders")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}