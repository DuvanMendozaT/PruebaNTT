package com.training.portal.controller;

import com.training.portal.dto.UserCourseModel;
import com.training.portal.dto.rest.UserCourseRequest;
import com.training.portal.service.usercourse.UserCourseService;
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
        name = "Cursos por Usuario",
        description = "Endpoints para asociar cursos a usuarios y actualizar su estado"
)
@RestController
@RequestMapping("/usercourse")
public class UserCourseController {

    @Autowired
    private UserCourseService userCourseService;

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
    @PostMapping("/create")
    public ResponseEntity<UserCourseModel> create(
            @RequestBody UserCourseRequest userCourseRequest
    ) {
        return ResponseEntity.ok(userCourseService.create(userCourseRequest));
    }

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
    @PostMapping("/update")
    public ResponseEntity<UserCourseModel> update(
            @RequestBody UserCourseRequest userCourseRequest
    ) {
        return ResponseEntity.ok(userCourseService.update(userCourseRequest));
    }
}
