package com.training.portal.controller.handler;
import com.training.portal.model.rest.SimpleResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ApiExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<SimpleResponse> handleNotFound(
            RuntimeException ex
    ) {
        log.error("recurso no entonntrado :" + ex.getMessage());

        SimpleResponse body = SimpleResponse.builder()
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<SimpleResponse> handleUserCourseExist(IllegalArgumentException ex){
        log.error("Fallo en la Solicitud :" + ex.getMessage());

        SimpleResponse body = SimpleResponse.builder()
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<SimpleResponse> handleAccessError(IllegalStateException ex){
        log.error("Fallo en el sistema :" + ex.getMessage());

        SimpleResponse body = SimpleResponse.builder()
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }
}
