package com.training.portal.service.user;

import com.training.portal.dto.*;
import com.training.portal.dto.rest.LoginRequest;
import com.training.portal.dto.rest.LoginResponse;
import com.training.portal.dto.rest.RegisterRequest;
import com.training.portal.dto.rest.SimpleResponse;
import com.training.portal.persistence.entity.UserEntity;
import com.training.portal.persistence.mapper.UserMapper;
import com.training.portal.persistence.repository.UserRepository;
import com.training.portal.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    private final Map<String, String> activeTokens = new ConcurrentHashMap<>();
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean passwordOk = passwordEncoder
                .matches(loginRequest.getPassword(), userEntity.getPasswordHash()
        );

        if (!passwordOk) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        String token = UUID.randomUUID().toString();
        activeTokens.put(token, userEntity.getEmail());

        return new LoginResponse(
                userEntity.getId(),
                token,
                userEntity.getEmail(),
                userEntity.getFullName(),
                userEntity.getRole()
        );
    }

    @Override
    public SimpleResponse register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())) throw new IllegalArgumentException("user exist");

        UserModel userModel = UserModel.builder()
                .email(registerRequest.getEmail())
                .fullName(registerRequest.getFullName())
                .passwordHash(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();

        userRepository.save(userMapper.toEntity(userModel));
        return SimpleResponse.builder().message(Constants.SUCCESFULLY).build();
    }
}
