package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.DisponibilidadeDTO;
import com.example.demo.dto.PacienteDTO;
import com.example.demo.service.PacienteService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Paciente", description = "Endpoints para gerenciamento de Paciente")
@RestController
@RequestMapping("api/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Operation(summary = "Lista Pacientes", description = "Lista pacientes cadastrados no sistema")
    @GetMapping
    public ResponseEntity<ApiResponse<List<PacienteDTO>>> listarPacientes() {
        try {
            List<PacienteDTO> paciente = pacienteService.listarTodos();
            ApiResponse<List<PacienteDTO>> response = new ApiResponse<>(paciente);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento invalido!", e.getMessage());
            ApiResponse<List<PacienteDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno!", e.getMessage());
            ApiResponse<List<PacienteDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Busca um paciente por ID", description = "Retorna os detalhes de um paciente específico")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<PacienteDTO>>> buscarPorId(@PathVariable Long id) {
        try {
            Optional<PacienteDTO> pacienteDTO = pacienteService.buscarPorId(id);
            ApiResponse<Optional<PacienteDTO>> response = new ApiResponse<>(pacienteDTO);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento invalido!", e.getMessage());
            ApiResponse<Optional<PacienteDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno!", e.getMessage());
            ApiResponse<Optional<PacienteDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Cria um novo Paciente", description = "Cadastra um novo paciente no sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<PacienteDTO>> criarPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        try {
            // Tenta salvar o paciente
            PacienteDTO savedPaciente = pacienteService.salvar(pacienteDTO);

            // Retorna sucesso com o PacienteDTO salvo
            ApiResponse<PacienteDTO> response = new ApiResponse<>(savedPaciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            // Cria um erro com a mensagem específica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<PacienteDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            // Cria um erro genérico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<PacienteDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Atualiza um Paciente", description = "Atualiza os dados de um paciente existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PacienteDTO>> atualizarPaciente(@PathVariable Long id,
            @RequestBody PacienteDTO pacienteDTO) {
        try {
            // Tenta salvar o paciente
            PacienteDTO paciente = pacienteService.atualizar(id, pacienteDTO);

            ApiResponse<PacienteDTO> response = new ApiResponse<>(paciente);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // Cria um erro com a mensagem específica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<PacienteDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            // Cria um erro genérico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<PacienteDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "inativar um paciente", description = "inativar um paciente do sistema pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PacienteDTO>> deletarPaciente(@PathVariable Long id) {
        try {
            PacienteDTO pacienteDto = pacienteService.inativar(id);

            ApiResponse<PacienteDTO> response = new ApiResponse<>(pacienteDto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<PacienteDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<PacienteDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "reativar um paciente", description = "reativar um paciente do sistema pelo ID")
    @PostMapping("/reativar/{id}")
    public ResponseEntity<ApiResponse<PacienteDTO>> reativarPaciente(@PathVariable Long id) {
        try {
            PacienteDTO pacienteDto = pacienteService.reativar(id);

            ApiResponse<PacienteDTO> response = new ApiResponse<>(pacienteDto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<PacienteDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<PacienteDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
