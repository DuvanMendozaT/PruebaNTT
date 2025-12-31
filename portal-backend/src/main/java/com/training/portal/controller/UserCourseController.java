package com.training.portal.controller;

import com.training.portal.controller.doc.UserCourseControllerDoc;
import com.training.portal.model.UserCourseModel;
import com.training.portal.model.rest.UserCourseRequest;
import com.training.portal.service.usercourse.UserCourseService;
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
public class UserCourseController implements UserCourseControllerDoc {

    @Autowired
    private UserCourseService userCourseService;

    @PostMapping("/create")
    public ResponseEntity<UserCourseModel> create(@RequestBody UserCourseRequest userCourseRequest) {
        return ResponseEntity.ok(userCourseService.create(userCourseRequest));
    }

    @PostMapping("/update")
    public ResponseEntity<UserCourseModel> update(@RequestBody UserCourseRequest userCourseRequest) {
        return ResponseEntity.ok(userCourseService.update(userCourseRequest));
    }
}
