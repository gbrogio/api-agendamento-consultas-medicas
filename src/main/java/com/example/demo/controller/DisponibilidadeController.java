package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entities.Disponibilidade;
import com.example.demo.service.DisponibilidadeService;

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
@RequestMapping("/medicos/{medicoId}/disponibilidades")

public class DisponibilidadeController {

    private final DisponibilidadeService disponibilidadeService;

    public DisponibilidadeController(DisponibilidadeService disponibilidadeService){
        this.disponibilidadeService = disponibilidadeService;
    }

    @Operation(summary = "Resgistra disponibilidade", description = "Cria um registro com dia e horários da consulta")
    @PostMapping
    public ResponseEntity<Disponibilidade> registrarDisponibilidade(
            @PathVariable Long medicoId,
            @RequestBody @Valid Disponibilidade request){

            Disponibilidade disponibilidade = disponibilidadeService.registrarDisponibilidade(
                    medicoId, request.getDiaDaSemana(), request.getHorarioInicio(), request.getHorarioFim());

            return ResponseEntity.status(HttpStatus.CREATED).body(disponibilidade);
        }

    @Operation(summary = "Lista disponibilidade", description = "Lista dias e horários disponíveis de um médico por Id")
    @GetMapping("/medicos/{medicoId}/disponibilidades")
    public ResponseEntity<List<Disponibilidade>> listarDisponibilidades(@PathVariable Long medicoId){
        List<Disponibilidade> disponibilidades = disponibilidadeService.listarDisponibilidade(medicoId);
        return ResponseEntity.ok(disponibilidades);
    }
    
    @Operation
    @DeleteMapping("/disponibilidades/{id}")
    public ResponseEntity<Void> removerDisponibilidade (@PathVariable Long Id){
        disponibilidadeService.removerDisponibilidade(Id);
        return ResponseEntity.noContent().build();
    }
}