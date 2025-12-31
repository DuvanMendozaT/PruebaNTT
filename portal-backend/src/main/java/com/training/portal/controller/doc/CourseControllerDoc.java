package com.training.portal.controller.doc;

import com.training.portal.model.CourseModel;
import com.training.portal.model.rest.CourseRequest;
import com.training.portal.model.rest.SimpleResponse;
import com.training.portal.model.rest.UserCourseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CourseControllerDoc {

    @Operation(
            summary = "Crear curso",
            description = "Crea un nuevo curso con la información enviada en el body"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Curso creado correctamente",
                    content = @Content(schema = @Schema(implementation = CourseModel.class))
            ),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<CourseModel> create(@RequestBody CourseRequest courseRequest);

    @Operation(
            summary = "Buscar curso por ID",
            description = "Retorna un curso según su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Curso encontrado",
                    content = @Content(schema = @Schema(implementation = CourseModel.class))
            ),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<CourseModel> findById(@Parameter(description = "ID del curso", example = "10") @PathVariable Long id);

    @Operation(
            summary = "Listar cursos por usuario",
            description = "Retorna la lista de cursos asociados a un usuario"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado retornado correctamente",
                    content = @Content(schema = @Schema(implementation = CourseModel.class))
            ),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado o sin cursos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<List<UserCourseResponse>> findByUserId(@Parameter(description = "ID del usuario", example = "5") @PathVariable Long id);

    @Operation(
            summary = "Actualizar curso",
            description = "Actualiza un curso con la información enviada en el body"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Curso actualizado correctamente",
                    content = @Content(schema = @Schema(implementation = CourseModel.class))
            ),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Curso no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<CourseModel> update(@RequestBody CourseRequest courseRequest);

    @Operation(
            summary = "Listar todos los cursos",
            description = "Retorna la lista completa de cursos"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado retornado correctamente",
                    content = @Content(schema = @Schema(implementation = CourseModel.class))
            ),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<List<CourseModel>> listCustomer();

    @Operation(
            summary = "Listar cursos por módulo",
            description = "Retorna cursos filtrados por el nombre del módulo (por ejemplo: 'backend', 'frontend', etc.)"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado filtrado retornado correctamente",
                    content = @Content(schema = @Schema(implementation = CourseModel.class))
            ),
            @ApiResponse(responseCode = "400", description = "Parámetro 'module' inválido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<List<CourseModel>> listCustomer(@Parameter(description = "Nombre del módulo", example = "backend") @PathVariable String module);

    @Operation(
            summary = "Eliminar Curso",
            description = "Eliminar un Curso del sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Curso eliminado correctamente",
                    content = @Content(schema = @Schema(implementation = SimpleResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    ResponseEntity<CourseModel> deleteById(@Parameter(description = "ID del curso", example = "10") @PathVariable Long id);
}
