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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MedicoDTO;
import com.example.demo.service.MedicoService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// Aqui você pode adicionar os métodos para gerenciar médicos, como listar, buscar por ID, 
//criar, atualizar e excluir médicos.

@Tag(name = "Medico", description = "Endpoints para gerenciamento de Médico")
@RestController
@RequestMapping("/api/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Operation(summary = "Cria um novo Médico", description = "Cadastra um novo médico no sistema")
    @PostMapping
    public ResponseEntity<ApiResponse<MedicoDTO>> criarMedico(@Valid @RequestBody MedicoDTO medicoDTO) {
        try {
            // para tentar salvar o medico
            MedicoDTO savedMedico = medicoService.salvar(medicoDTO);

            // aqui retorna sucesso o MedicoDTO salvo
            ApiResponse<MedicoDTO> response = new ApiResponse<>(savedMedico);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            // da um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage(), null);
            ApiResponse<MedicoDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage(), null);
            ApiResponse<MedicoDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Atualizar o Médico", description = "Atualiza os dados de um médico existente")
    @PutMapping("/{id}") // Fixed the path from "path/{id}" to "/{id}"
    public ResponseEntity<ApiResponse<MedicoDTO>> atualizarPaciente(@PathVariable Long id,
            @Valid @RequestBody MedicoDTO medicoDTO) {
        try {
            // para tentar salvar o medico
            MedicoDTO medico = medicoService.atualizar(id, medicoDTO);

            // aqui retorna sucesso o MedicoDTO salvo
            ApiResponse<MedicoDTO> response = new ApiResponse<>(medico);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // da um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage(), null);
            ApiResponse<MedicoDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage(), null);
            ApiResponse<MedicoDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Lista todos os médico", description = "Retorna uma lista com todos os médico cadastrados")
    @GetMapping
    public ResponseEntity<ApiResponse<List<MedicoDTO>>> listarMedicos() {
        try {
            // tenta listar todos os médicos
            List<MedicoDTO> medicos = medicoService.listarTodos();
            // retorna sucesso com a lista de médicos
            ApiResponse<List<MedicoDTO>> response = new ApiResponse<>(medicos);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // da um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage(), null);
            ApiResponse<List<MedicoDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage(), null);
            ApiResponse<List<MedicoDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Busca um médico por ID", description = "Retorna os detalhes de um médico específico")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<MedicoDTO>>> buscaPorId(@PathVariable Long id) {
        try {
            // tenta buscar o médico pelo ID
            Optional<MedicoDTO> medicoDTO = medicoService.buscarPorId(id);
            ApiResponse<Optional<MedicoDTO>> response = new ApiResponse<>(medicoDTO);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            // da um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage(), null);
            ApiResponse<Optional<MedicoDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage(), null);
            ApiResponse<Optional<MedicoDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Inativar um médico", description = "Remove um médico do sistema pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicoDTO>> deletarMedico(@PathVariable Long id) {
        try {
            // tenta deletar o medico
            MedicoDTO medicoDTO = medicoService.deletar(id);
            // retorna sucesso
            ApiResponse<MedicoDTO> response = new ApiResponse<>(medicoDTO);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // da um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage(), null);
            ApiResponse<MedicoDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage(), null);
            ApiResponse<MedicoDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Reativar um médico", description = "Remove um médico do sistema pelo ID")
    @PutMapping("/reativar/{id}")
    public ResponseEntity<ApiResponse<MedicoDTO>> reativarMedico(@PathVariable Long id) {
        try {
            // tenta reativar o medico
            MedicoDTO medicoDTO = medicoService.reativar(id);
            ApiResponse<MedicoDTO> response = new ApiResponse<>(medicoDTO);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            // da um erro com a mensagem especifica
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage(), null);
            ApiResponse<MedicoDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage(), null);
            ApiResponse<MedicoDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
