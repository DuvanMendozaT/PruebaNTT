package com.training.portal.controller.doc;

import com.training.portal.model.UserModel;
import com.training.portal.model.rest.LoginRequest;
import com.training.portal.model.rest.LoginResponse;
import com.training.portal.model.rest.RegisterRequest;
import com.training.portal.model.rest.SimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface AuthControllerDoc {

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
    ResponseEntity<String> login(@RequestBody LoginRequest loginRequest);

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
    ResponseEntity<SimpleResponse> register(@RequestBody RegisterRequest registerRequest);

    @Operation(
            summary = "Eliminar usuario",
            description = "Eliminar un usuario del sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario eliminado correctamente",
                    content = @Content(schema = @Schema(implementation = SimpleResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<UserModel> deleteById(@Parameter(description = "ID del user", example = "10") @PathVariable Long id);

    @Operation(
            summary = "Listar usuarios",
            description = "Lista todos los usuarios del sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuarios consultados correctamente",
                    content = @Content(schema = @Schema(implementation = SimpleResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<List<UserModel>> listCustomer();
}
