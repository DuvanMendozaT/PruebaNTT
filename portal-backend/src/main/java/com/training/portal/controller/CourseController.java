package com.training.portal.controller;

import com.training.portal.controller.doc.CourseControllerDoc;
import com.training.portal.model.CourseModel;
import com.training.portal.model.rest.CourseRequest;
import com.training.portal.model.rest.UserCourseResponse;
import com.training.portal.service.course.CourseService;
import io.swagger.v3.oas.annotations.Parameter;
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
public class CourseController implements CourseControllerDoc {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<CourseModel> create(@RequestBody CourseRequest courseRequest) {
        return ResponseEntity.ok(courseService.create(courseRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseModel> findById(@Parameter(description = "ID del curso", example = "10") @PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserCourseResponse>> findByUserId(@Parameter(description = "ID del usuario", example = "5") @PathVariable Long id) {
        return ResponseEntity.ok(courseService.findByUserId(id));
    }

    @PutMapping("/update")
    public ResponseEntity<CourseModel> update(@RequestBody CourseRequest courseRequest) {
        return ResponseEntity.ok(courseService.update(courseRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseModel>> listCustomer() {
        return ResponseEntity.ok(courseService.findAll());
    }


    @GetMapping("/all/{module}")
    public ResponseEntity<List<CourseModel>> listCustomer(@Parameter(description = "Nombre del módulo", example = "backend") @PathVariable String module) {
        return ResponseEntity.ok(courseService.findByModule(module));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseModel> deleteById(@Parameter(description = "ID del curso", example = "10") @PathVariable Long id) {
        return ResponseEntity.ok(courseService.deleteById(id));
    }
}
