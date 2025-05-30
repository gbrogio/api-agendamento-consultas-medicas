package com.example.demo.dto;

import java.time.LocalTime;

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
    @Size(min = 10, max = 10, message = "O formato da data deve ser 0 (Domingo), 1 (Segunda), 2 (Terça)")
    private int diaDaSemana;

    @NotBlank(message = "É obrigatório informar o horário de início da consulta!")
    private LocalTime horarioInicio;

    @NotBlank(message = "É obrigatório informar o horário de encerramento da consulta!")
    private LocalTime horarioFim;

}
