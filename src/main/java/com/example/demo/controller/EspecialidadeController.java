package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EspecialidadeDTO;
import com.example.demo.service.EspecialidadeService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Especialidades", description = "Endpoints para gerenciamento de especialidades")
@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @Operation(summary = "Lista todas as especialidades", description = "Retorna uma lista com todas as especialidades cadastradas")
    @GetMapping
    public ResponseEntity<ApiResponse<List<EspecialidadeDTO>>> listarEspecialidades() {
        try {
            List<EspecialidadeDTO> especialidades = especialidadeService.listarTodos();
            ApiResponse<List<EspecialidadeDTO>> response = new ApiResponse<>(especialidades);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<List<EspecialidadeDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<List<EspecialidadeDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Busca uma especialidade por ID", description = "Retorna os detalhes de uma especialidade específica")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EspecialidadeDTO>> buscarPorId(@PathVariable Long id) {
        try {
            Optional<EspecialidadeDTO> especialidadeDTO = especialidadeService.buscarPorId(id);
            if (especialidadeDTO.isPresent()) {
                ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(especialidadeDTO.get());
                return ResponseEntity.ok(response);
            } else {
                ErrorResponse errorResponse = new ErrorResponse("Não encontrado",
                        "Especialidade com ID " + id + " não encontrada");
                ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(errorResponse);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Cria uma nova especialidade", description = "Cadastra uma nova especialidade no sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<EspecialidadeDTO>> criarEspecialidade(
            @Valid @RequestBody EspecialidadeDTO especialidadeDTO) {
        try {
            // Tenta salvar a especialidade
            EspecialidadeDTO savedEspecialidade = especialidadeService.salvar(especialidadeDTO);

            // Retorna sucesso com a EspecialidadeDTO salva
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(savedEspecialidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            // Cria um erro com a mensagem específica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            // Cria um erro genérico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Deleta uma especialidade", description = "Remove uma especialidade do sistema pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deletarEspecialidade(@PathVariable Long id) {
        try {
            especialidadeService.deletar(id);
            ApiResponse<String> response = new ApiResponse<>("Especialidade removida com sucesso");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<String> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<String> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
