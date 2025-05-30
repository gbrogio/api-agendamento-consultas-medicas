package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EspecialidadeDTO;
import com.example.demo.service.EspecialidadeService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Aqui você pode adicionar os métodos para gerenciar especialidades, como listar, buscar por ID, 
//criar, atualizar e excluir especialidades.

@Tag(name = "Especialidade", description = "Endpoints para gerenciamento da especialidade")
@RestController
@RequestMapping("api/especialidade")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @Operation(summary = "Cria uma nova especialidade", description = "Cadastra uma nova especialidade no sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<EspecialidadeDTO>> criarEspecialidade(@Valid @RequestBody EspecialidadeDTO especialidadeDTO) {
        try {
            // para tentar salvar o especialidade
            EspecialidadeDTO savedEspecialidade = especialidadeService.salvar(especialidadeDTO);

            // aqui retorna sucesso o EspecialidadeDTO salvo
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(savedEspecialidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            // da um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            System.out.println(especialidadeDTO.toString());
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Atualizar a Especialidade", description = "Atualiza os dados de uma especialidade existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EspecialidadeDTO>> atualizarPaciente(@PathVariable Long id,
            @RequestBody EspecialidadeDTO especialidadeDTO) {
        try {
            // para tentar salvar o especialidade
            EspecialidadeDTO especialidade = especialidadeService.atualizar(id, especialidadeDTO);

            // aqui retorna sucesso o EspecialidadeDTO salvo
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(especialidade);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // da um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Lista todas as especialidades", description = "Retorna uma lista com todos as especialidades")
    @GetMapping
    public ResponseEntity<ApiResponse<List<EspecialidadeDTO>>> listarEspecialidades() {
        try {
            // tenta listar todos as especialidade
            List<EspecialidadeDTO> especialidades = especialidadeService.listarTodos();
            // retorna sucesso com a lista de especialidades
            ApiResponse<List<EspecialidadeDTO>> response = new ApiResponse<>(especialidades);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // da um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<List<EspecialidadeDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<List<EspecialidadeDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Busca uma especialidade por ID", description = "Retorna os detalhes de uma especialidade específico")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<EspecialidadeDTO>>> buscaPorId(@PathVariable Long id) {
        try {
            // tenta buscar a especialidades pelo ID
            Optional<EspecialidadeDTO> especialidadeDTO = especialidadeService.buscarPorId(id);
            ApiResponse<Optional<EspecialidadeDTO>> response = new ApiResponse<>(especialidadeDTO);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // da um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<Optional<EspecialidadeDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<Optional<EspecialidadeDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Inativar uma especialidade", description = "Remove uma especialidade do sistema pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<EspecialidadeDTO>> deletarEspecialidade(@PathVariable Long id) {
        try {
            // tenta deletar o especialidade
            EspecialidadeDTO especialidadeDTO = especialidadeService.deletar(id);
            // retorna sucesso
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(especialidadeDTO);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // da um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<EspecialidadeDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
