package com.training.portal.service.user;

import com.training.portal.model.*;
import com.training.portal.model.rest.LoginRequest;
import com.training.portal.model.rest.LoginResponse;
import com.training.portal.model.rest.RegisterRequest;
import com.training.portal.model.rest.SimpleResponse;
import com.training.portal.persistence.entity.UserEntity;
import com.training.portal.persistence.mapper.UserMapper;
import com.training.portal.persistence.repository.UserRepository;
import com.training.portal.service.jwt.JwtService;
import com.training.portal.util.Constants;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtService jwtService;

    @Override
    @Transactional
    public String login(LoginRequest loginRequest) {
        log.info("inicio servicio Login");

        UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean passwordOk = passwordEncoder
                .matches(loginRequest.getPassword(), userEntity.getPasswordHash()
        );

        if (!passwordOk) {
            log.error("Credenciales incorrectas");
            throw new IllegalArgumentException("Credenciales inválidas");
        }


        return jwtService.generateToken(
                userEntity.getEmail(),
                Map.of(
                        "id", userEntity.getId(),
                        "email", userEntity.getEmail(),
                        "fullName", userEntity.getFullName(),
                        "role", userEntity.getRole()
                )
        );
    }

    @Override
    @Transactional
    public SimpleResponse register(RegisterRequest registerRequest) {
        log.info("inicio servicio registro usuario");

        if(userRepository.existsByEmail(registerRequest.getEmail())) throw new IllegalArgumentException("user exist");

        UserModel userModel = UserModel.builder()
                .email(registerRequest.getEmail())
                .fullName(registerRequest.getFullName())
                .passwordHash(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();

        userRepository.save(userMapper.toEntity(userModel));
        log.info("Registro exitoso");

        return SimpleResponse.builder().message(Constants.SUCCESFULLY).build();
    }

    @Override
    @Transactional
    public UserModel deleteById(Long id) {

        UserEntity existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        userRepository.deleteById(id);

        return userMapper.toModel(existing);
    }

    @Override
    public List<UserModel> findAll() {
        log.info("inicio servicio consulta de todos los cursos");
        return userMapper.toModels(userRepository.findAll());
    }
}
