package com.free.archecode.shared.config;

import com.free.archecode.shared.exceptions.InvalidRoleException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/*
Так как ошибки о всяческой валидации и прочее не обрабатываются, нужно самостоятельно
обработать их и выдать в каком либо требуемом формате.
Своего рода регистрация ошибки и решение что с ней делать.

Например - вернуть JSON с ошибками, которые выпадают, и выдать ответ.
Spring, как никак, не только для сайтов.

Конкретно тут - можно контролировать все исключения и ошибки.

Тот же MethodArgumentNotValidException - это техническая ошибка, которая случается еще ДО попадания в контроллер.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final String prefix = "error";

    // валидация
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(403).body(new HashMap<>());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthorization(Exception ex) {
        return ResponseEntity.status(401).build();
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<Map<String, String>> handleInvalidRole(InvalidRoleException ex) {
        return ResponseEntity.badRequest().body(Map.of(prefix, ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(Map.of(prefix, "Invalid JSON"));
    }

    // все остальное
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put(prefix, ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}