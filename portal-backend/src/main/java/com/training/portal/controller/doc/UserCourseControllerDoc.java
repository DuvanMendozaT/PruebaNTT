package com.training.portal.controller.doc;

import com.training.portal.model.UserCourseModel;
import com.training.portal.model.rest.UserCourseRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserCourseControllerDoc {

    @Operation(
            summary = "Asignar curso a usuario",
            description = "Asocia un curso a un usuario"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Curso asignado correctamente",
                    content = @Content(schema = @Schema(implementation = UserCourseModel.class))
            ),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuario o curso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    ResponseEntity<UserCourseModel> create(@RequestBody UserCourseRequest userCourseRequest);

    @Operation(
            summary = "Actualizar curso de usuario",
            description = "Actualiza el estado o información del curso asignado a un usuario"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Curso de usuario actualizado correctamente",
                    content = @Content(schema = @Schema(implementation = UserCourseModel.class))
            ),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Relación usuario-curso no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<UserCourseModel> update(@RequestBody UserCourseRequest userCourseRequest);
}
