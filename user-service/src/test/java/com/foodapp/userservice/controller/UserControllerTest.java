package com.foodapp.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.userservice.dto.*;
import com.foodapp.userservice.filter.JwtFilter;
import com.foodapp.userservice.service.CustomUserDetailsService;
import com.foodapp.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;   // ✅ FIXED
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(properties = {
        "eureka.client.enabled=false"
})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void testRegisterUser() throws Exception {

        UserRequest request = new UserRequest();
        request.setName("Arman");
        request.setEmail("arman@gmail.com");
        request.setPassword("123456");

        UserResponse response = new UserResponse();
        response.setName("Arman");
        response.setEmail("arman@gmail.com");

        when(userService.register(any(UserRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("arman@gmail.com"));
    }

    @Test
    void testLoginUser() throws Exception {

        LoginRequest request = new LoginRequest();
        request.setEmail("arman@gmail.com");
        request.setPassword("123456");

        LoginResponse response = new LoginResponse();
        response.setToken("dummy-jwt-token");

        when(userService.login(any(LoginRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("dummy-jwt-token"));
    }
}