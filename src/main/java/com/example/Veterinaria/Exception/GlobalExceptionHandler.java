package com.example.Veterinaria.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<Map<String, String>>
    recursoNoEncontrado(
            ResourceNotFoundException ex
    ) {

        Map<String, String> respuesta =
                new HashMap<>();

        respuesta.put(
                "error",
                ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(respuesta);
    }


    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<Map<String, String>>
    validaciones(
            MethodArgumentNotValidException ex
    ) {

        Map<String, String> errores =
                new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->

                        errores.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )

                );

        return ResponseEntity
                .badRequest()
                .body(errores);
    }


    @ExceptionHandler(
            IllegalArgumentException.class
    )
    public ResponseEntity<Map<String, String>>
    argumentoInvalido(
            IllegalArgumentException ex
    ) {

        return ResponseEntity
                .badRequest()
                .body(
                        Map.of(
                                "error",
                                ex.getMessage()
                        )
                );
    }
}
