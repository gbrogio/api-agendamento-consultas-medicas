package com.example.demo.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisponibilidadeDTO {

    private Long id;
    private Long medicoId;
    
    @NotBlank(message = "É obrigatório informar a data da consulta!")
    @Size(min = 10, max = 10, message ="O formato da data deve ser dd/mm/aaaa")
    @Future(message = "O dia da consulta é uma data futura!")
    private String diaDaSemana;

    @NotBlank(message = "É obrigatório informar o horário de início da consulta!")
    @Size(min = 5, max = 5, message = "O formato de horário deve ser hh:mm")
    private LocalTime horarioInicio;

    @NotBlank(message = "É obrigatório informar o horário de encerramento da consulta!")
    @Size(min = 5, max = 5, message = "O formato de horário deve ser hh:mm")
    private LocalTime horarioFim;

}
