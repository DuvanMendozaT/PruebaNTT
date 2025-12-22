package com.training.portal.service.user;

import com.training.portal.dto.rest.LoginRequest;
import com.training.portal.dto.rest.LoginResponse;
import com.training.portal.dto.rest.RegisterRequest;
import com.training.portal.dto.rest.SimpleResponse;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest) throws IllegalAccessException;

    SimpleResponse register(RegisterRequest registerRequest);
}
