package com.foodapp.userservice.service;

import com.foodapp.userservice.dto.LoginRequest;
import com.foodapp.userservice.entity.Role;
import com.foodapp.userservice.entity.User;
import com.foodapp.userservice.repository.RoleRepository;
import com.foodapp.userservice.repository.UserRepository;
import com.foodapp.userservice.service.impl.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        Role role = new Role(1L, "ROLE_USER", null);

        user = User.builder()
                .id(1L)
                .email("test@gmail.com")
                .password("encodedPass")
                .roles(Set.of(role))
                .build();
    }

    @Test
    void login_shouldReturnToken_whenCredentialsValid() {

        LoginRequest request =
                new LoginRequest("test@gmail.com", "1234");

        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("1234", "encodedPass"))
                .thenReturn(true);

        when(jwtService.generateToken(user))
                .thenReturn("mockToken");

        var response = userService.login(request);

        assertEquals("mockToken", response.getToken());
        verify(userRepository, times(1))
                .findByEmail("test@gmail.com");
    }

    @Test
    void login_shouldThrowException_whenPasswordInvalid() {

        LoginRequest request =
                new LoginRequest("test@gmail.com", "wrong");

        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("wrong", "encodedPass"))
                .thenReturn(false);

        assertThrows(RuntimeException.class,
                () -> userService.login(request));
    }
}