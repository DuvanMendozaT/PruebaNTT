package com.training.portal.controller.handler;
import com.training.portal.dto.rest.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<SimpleResponse> handleNotFound(
            RuntimeException ex
    ) {
        SimpleResponse body = SimpleResponse.builder()
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<SimpleResponse> handleUserCourseExist(IllegalArgumentException ex){
        SimpleResponse body = SimpleResponse.builder()
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<SimpleResponse> handleAccessError(IllegalStateException ex){
        SimpleResponse body = SimpleResponse.builder()
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}
