package com.training.portal.controller;

import com.training.portal.dto.CourseModel;
import com.training.portal.dto.rest.CourseRequest;
import com.training.portal.dto.rest.UserCourseResponse;
import com.training.portal.service.course.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Cursos",
        description = "Endpoints para crear, consultar y actualizar cursos, incluyendo consultas por usuario y por módulo"
)
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

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
    @PostMapping("/create")
    public ResponseEntity<CourseModel> create(
            @RequestBody CourseRequest courseRequest
    ) {
        return ResponseEntity.ok(courseService.create(courseRequest));
    }

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
    @GetMapping("/{id}")
    public ResponseEntity<CourseModel> findById(
            @Parameter(description = "ID del curso", example = "10")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(courseService.findById(id));
    }

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
    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserCourseResponse>> findByUserId(
            @Parameter(description = "ID del usuario", example = "5")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(courseService.findByUserId(id));
    }

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
    @PutMapping("/update")
    public ResponseEntity<CourseModel> update(
            @RequestBody CourseRequest courseRequest
    ) {
        return ResponseEntity.ok(courseService.update(courseRequest));
    }

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
    @GetMapping("/all")
    public ResponseEntity<List<CourseModel>> listCustomer() {
        return ResponseEntity.ok(courseService.findAll());
    }

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
    @GetMapping("/all/{module}")
    public ResponseEntity<List<CourseModel>> listCustomer(
            @Parameter(description = "Nombre del módulo", example = "backend")
            @PathVariable String module
    ) {
        return ResponseEntity.ok(courseService.findByModule(module));
    }
}
