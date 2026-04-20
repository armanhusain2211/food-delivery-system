package com.foodapp.userservice.service;

import com.foodapp.userservice.dto.*;

public interface UserService {

    UserResponse register(UserRequest request);

    UserResponse getUserByEmail(String email);

    UserResponse getCurrentUser();

    LoginResponse login(LoginRequest request);
}