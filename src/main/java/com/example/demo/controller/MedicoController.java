package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MedicoDTO;
import com.example.demo.service.MedicoService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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

// Aqui você pode adicionar os métodos para gerenciar médicos, como listar, buscar por ID, 
//criar, atualizar e excluir médicos.

@Tag(name = "Medico", description = "Endpoints para gerenciamento de Médico")
@RestController
@RequestMapping("api/Medico")
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
            ErrorResponse errorResponse = new ErrorResponse("Argumento inválido", e.getMessage());
            ApiResponse<MedicoDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            // cria um erro generico
            ErrorResponse errorResponse = new ErrorResponse("Erro interno", e.getMessage());
            ApiResponse<MedicoDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Lista todos os médico", description = "Retorna uma lista com todos os médico cadastrados")
    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listarMedico() {
        List<MedicoDTO> medico = medicoService.listarTodos();
        return ResponseEntity.ok(medico);
    }

    @Operation(summary = "Busca um médico por ID", description = "Retorna os detalhes de um médico específico")
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> buscaPorId(@PathVariable Long id) {
        Optional<MedicoDTO> medicoDTO = medicoService.buscarPorId(id);
        return medicoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um médico", description = "Remove um médico do sistema pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
