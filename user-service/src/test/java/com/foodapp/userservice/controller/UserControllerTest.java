package com.foodapp.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapp.userservice.dto.LoginRequest;
import com.foodapp.userservice.dto.LoginResponse;
import com.foodapp.userservice.dto.UserRequest;
import com.foodapp.userservice.dto.UserResponse;
import com.foodapp.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

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