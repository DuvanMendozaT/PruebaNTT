package com.training.portal.service.user;

import com.training.portal.model.UserModel;
import com.training.portal.model.rest.LoginRequest;
import com.training.portal.model.rest.LoginResponse;
import com.training.portal.model.rest.RegisterRequest;
import com.training.portal.model.rest.SimpleResponse;

import java.util.List;

public interface UserService {
    String login(LoginRequest loginRequest);

    SimpleResponse register(RegisterRequest registerRequest);

    UserModel deleteById(Long id);

    List<UserModel> findAll();
}
