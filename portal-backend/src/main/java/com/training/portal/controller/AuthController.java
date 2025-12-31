package com.training.portal.controller;

import com.training.portal.controller.doc.AuthControllerDoc;
import com.training.portal.model.UserModel;
import com.training.portal.model.rest.LoginRequest;
import com.training.portal.model.rest.LoginResponse;
import com.training.portal.model.rest.RegisterRequest;
import com.training.portal.model.rest.SimpleResponse;
import com.training.portal.service.user.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Autenticación",
        description = "Endpoints relacionados con autenticación y registro de usuarios"
)
@RestController
@RequestMapping("/auth")
public class AuthController implements AuthControllerDoc {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }
    @PostMapping("/register")
    public ResponseEntity<SimpleResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.register(registerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserModel> deleteById(@Parameter(description = "ID del user", example = "10") @PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @GetMapping()
    public ResponseEntity<List<UserModel>> listCustomer() {
        return ResponseEntity.ok(userService.findAll());
    }
}
