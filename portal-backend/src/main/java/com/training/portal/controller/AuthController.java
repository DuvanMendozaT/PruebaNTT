package com.training.portal.controller;

import com.training.portal.dto.rest.LoginRequest;
import com.training.portal.dto.rest.LoginResponse;
import com.training.portal.dto.rest.RegisterRequest;
import com.training.portal.dto.rest.SimpleResponse;
import com.training.portal.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Autenticación",
        description = "Endpoints relacionados con autenticación y registro de usuarios"
)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica un usuario y retorna la información necesaria para la sesión"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Autenticación exitosa",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class))
            ),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
    ) throws IllegalAccessException {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @Operation(
            summary = "Registrar usuario",
            description = "Registra un nuevo usuario en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario registrado correctamente",
                    content = @Content(schema = @Schema(implementation = SimpleResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/register")
    public ResponseEntity<SimpleResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(userService.register(registerRequest));
    }
}
