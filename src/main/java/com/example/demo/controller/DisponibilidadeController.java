package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DisponibilidadeDTO;
import com.example.demo.service.DisponibilidadeService;
import com.example.demo.service.Utils.ApiResponse;
import com.example.demo.service.Utils.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Disponibilidade", description = "Endpoints para verificar disponibilidade de um médico")
@RestController
@RequestMapping("/api/medicos/{medicoId}/disponibilidades")

public class DisponibilidadeController {

    @Autowired
    private DisponibilidadeService disponibilidadeService;

    @Operation(summary = "Resgistra disponibilidade", description = "Cria um registro com dia e horários da consulta")
    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<ApiResponse<DisponibilidadeDTO>> registrarDisponibilidade( @PathVariable Long medicoId,
    @RequestBody @Valid DisponibilidadeDTO disponibilidadeDTO){
        try{
            DisponibilidadeDTO disponibilidadeSalva = disponibilidadeService.registrarDisponibilidade(medicoId, disponibilidadeDTO);
            ApiResponse<DisponibilidadeDTO> response = new ApiResponse<>(disponibilidadeSalva);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch(IllegalArgumentException e){
=======
    public ResponseEntity<ApiResponse<DisponibilidadeDTO>> registrarDisponibilidade(@PathVariable Long medicoId,
            @RequestBody @Valid DisponibilidadeDTO disponibilidadeDTO) {
        try {
            DisponibilidadeDTO disponibilidadeSalva = disponibilidadeService.registrarDisponibilidade(medicoId,
                    disponibilidadeDTO);
            ApiResponse<DisponibilidadeDTO> response = new ApiResponse<>(disponibilidadeSalva);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
>>>>>>> homolog
            ErrorResponse errorResponse = new ErrorResponse("Erro humano!", e.getMessage());
            ApiResponse<DisponibilidadeDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

<<<<<<< HEAD
        } catch(Exception e){
=======
        } catch (Exception e) {
>>>>>>> homolog
            ErrorResponse errorResponse = new ErrorResponse("Falha no sistema!", e.getMessage());
            ApiResponse<DisponibilidadeDTO> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "Lista disponibilidade", description = "Lista dias e horários disponíveis de um médico por Id")
    @GetMapping
<<<<<<< HEAD
    public ResponseEntity<ApiResponse<List<DisponibilidadeDTO>>> listarDisponibilidades(@PathVariable Long medicoId){
        try{
=======
    public ResponseEntity<ApiResponse<List<DisponibilidadeDTO>>> listarDisponibilidades(@PathVariable Long medicoId) {
        try {
>>>>>>> homolog
            List<DisponibilidadeDTO> disponibilidades = disponibilidadeService.listarDisponibilidade(medicoId);
            ApiResponse<List<DisponibilidadeDTO>> response = new ApiResponse<>(disponibilidades);
            return ResponseEntity.ok(response);

<<<<<<< HEAD
        }catch(IllegalArgumentException e){
=======
        } catch (IllegalArgumentException e) {
>>>>>>> homolog
            ErrorResponse errorResponse = new ErrorResponse("Erro humano!", e.getMessage());
            ApiResponse<List<DisponibilidadeDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);

<<<<<<< HEAD
        } catch(Exception e){
=======
        } catch (Exception e) {
>>>>>>> homolog
            ErrorResponse errorResponse = new ErrorResponse("Falha no sistema!", e.getMessage());
            ApiResponse<List<DisponibilidadeDTO>> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation
    @DeleteMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<ApiResponse<String>> removerDisponibilidade (@PathVariable Long id){
        try{
=======
    public ResponseEntity<ApiResponse<String>> removerDisponibilidade(@PathVariable Long id) {
        try {
>>>>>>> homolog
            disponibilidadeService.removerDisponibilidade(id);
            ApiResponse<String> response = new ApiResponse<>("Disponibilidade removida!");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

<<<<<<< HEAD
        } catch(IllegalArgumentException e){
            ErrorResponse errorResponse = new ErrorResponse("Erro humano!", e.getMessage());
            ApiResponse<String> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);
        } catch(Exception e){
=======
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro humano!", e.getMessage());
            ApiResponse<String> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
>>>>>>> homolog
            ErrorResponse errorResponse = new ErrorResponse("Falha no sistema!", e.getMessage());
            ApiResponse<String> response = new ApiResponse<>(errorResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
<<<<<<< HEAD
        
=======

>>>>>>> homolog
    }
}