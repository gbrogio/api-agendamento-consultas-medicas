package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse("Erro de validação", "Verifique os dados enviados", errors);
        ApiResponse<Map<String, String>> response = new ApiResponse<>(errorResponse);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        Map<String, String> fields = new HashMap<>();

        if (cause instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) cause;
            if (!ife.getPath().isEmpty()) {
                String fieldName = ife.getPath().get(0).getFieldName();
                String errorMessage = "Valor inválido: " + ife.getValue();
                fields.put(fieldName, errorMessage);
            }
            ErrorResponse errorResponse = new ErrorResponse("Erro de formatação", "Verifique os dados enviados", fields);
            ApiResponse<Map<String, String>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        ErrorResponse errorResponse = new ErrorResponse("Erro de leitura", "Dados inválidos fornecidos", fields);
        ApiResponse<Map<String, String>> response = new ApiResponse<>(errorResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
