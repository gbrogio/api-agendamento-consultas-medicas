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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.validation.ConstraintViolationException;

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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
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
            ErrorResponse errorResponse = new ErrorResponse("Erro de formatação", "Verifique os dados enviados",
                    fields);
            ApiResponse<Map<String, String>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        ErrorResponse errorResponse = new ErrorResponse("Erro de leitura", "Dados inválidos fornecidos", fields);
        ApiResponse<Map<String, String>> response = new ApiResponse<>(errorResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @SuppressWarnings("null")
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconhecido";
        String errorMessage = String.format("Tipo inválido para o parâmetro '%s': esperado %s, recebido %s",
                ex.getName(), requiredType, ex.getValue());
        ErrorResponse errorResponse = new ErrorResponse("Erro de tipo", errorMessage, null);
        ApiResponse<String> response = new ApiResponse<>(errorResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
