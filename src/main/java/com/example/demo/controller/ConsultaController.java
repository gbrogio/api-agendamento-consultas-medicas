package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ConsultaDTO;
import com.example.demo.service.ConsultaService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Consultas", description = "Endpoints para gerenciamento de consultas médicas")
@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Operation(summary = "Agendar consulta", description = "Agenda uma nova consulta médica")
    @PostMapping
    public ResponseEntity<ApiResponse<ConsultaDTO>> agendar(@Valid @RequestBody ConsultaDTO consultaDTO) {
        try {
            ConsultaDTO consultaSalva = consultaService.agendar(consultaDTO);
            ApiResponse<ConsultaDTO> response = new ApiResponse<>(consultaSalva);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Argumento inválido", e.getMessage(), null);
            ApiResponse<ConsultaDTO> response = new ApiResponse<>(error);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse("Erro interno", e.getMessage(), null);
            ApiResponse<ConsultaDTO> response = new ApiResponse<>(error);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Listar consultas", description = "Lista todas as consultas com filtros opcionais")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ConsultaDTO>>> listar(
            @RequestParam(name = "medico-id", required = false) @Parameter(description = "ID do médico para filtrar consultas", example = "1") Long medicoId,
            @RequestParam(name = "paciente-id", required = false) @Parameter(description = "ID do paciente para filtrar consultas", example = "1") Long pacienteId,
            @RequestParam(required = false) @Parameter(description = "Status da consulta. Ex.: agendada, concluida, cancelada", example = "agendada") String status,
            @RequestParam(name = "data-inicio", required = false) @Parameter(description = "Data e hora de início para filtrar consultas", example = "2023-10-01T10:00:00") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(name = "data-fim", required = false) @Parameter(description = "Data e hora de fim para filtrar consultas", example = "2023-10-31T18:00:00") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {

        try {
            List<ConsultaDTO> consultas = consultaService.listar(medicoId, pacienteId, status, dataInicio, dataFim);
            ApiResponse<List<ConsultaDTO>> response = new ApiResponse<>(consultas);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Argumento inválido", e.getMessage(), null);
            ApiResponse<List<ConsultaDTO>> response = new ApiResponse<>(error);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse("Erro interno", e.getMessage(), null);
            ApiResponse<List<ConsultaDTO>> response = new ApiResponse<>(error);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Buscar consulta por ID", description = "Retorna os detalhes de uma consulta específica")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultaDTO>> buscarPorId(@PathVariable Long id) {
        Optional<ConsultaDTO> consultaOpt = consultaService.buscarPorId(id);

        if (consultaOpt.isPresent()) {
            ApiResponse<ConsultaDTO> response = new ApiResponse<>(consultaOpt.get());
            return ResponseEntity.ok(response);
        } else {
            ErrorResponse error = new ErrorResponse("Não encontrada", "Consulta não encontrada", null);
            ApiResponse<ConsultaDTO> response = new ApiResponse<>(error);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @Operation(summary = "Atualizar status da consulta", description = "Atualiza o status de uma consulta específica")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<ConsultaDTO>> atualizarStatus(@PathVariable Long id,
            @RequestParam String status) {

        try {
            ConsultaDTO consultaAtualizada = consultaService.atualizarStatus(id, status);
            ApiResponse<ConsultaDTO> response = new ApiResponse<>(consultaAtualizada);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Argumento inválido", e.getMessage(), null);
            ApiResponse<ConsultaDTO> response = new ApiResponse<>(error);

            // Se a consulta não foi encontrada, retorna 404, senão retorna 400 para status
            // inválido
            HttpStatus httpStatus = e.getMessage().contains("não encontrada") ? HttpStatus.NOT_FOUND
                    : HttpStatus.BAD_REQUEST;

            return ResponseEntity.status(httpStatus).body(response);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse("Erro interno", e.getMessage(), null);
            ApiResponse<ConsultaDTO> response = new ApiResponse<>(error);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Cancelar consulta", description = "Cancela uma consulta (permitido até 24h antes)")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> cancelar(@PathVariable Long id) {
        try {
            consultaService.cancelar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse("Argumento inválido", e.getMessage(), null);
            ApiResponse<Void> response = new ApiResponse<>(error);

            // Determinar o status HTTP apropriado baseado na mensagem de erro
            HttpStatus httpStatus;
            if (e.getMessage().contains("não encontrada")) {
                httpStatus = HttpStatus.NOT_FOUND;
            } else if (e.getMessage().contains("24 horas")) {
                httpStatus = HttpStatus.CONFLICT; // Regra de negócio violada
            } else {
                httpStatus = HttpStatus.BAD_REQUEST;
            }

            return ResponseEntity.status(httpStatus).body(response);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse("Erro interno", e.getMessage(), null);
            ApiResponse<Void> response = new ApiResponse<>(error);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
