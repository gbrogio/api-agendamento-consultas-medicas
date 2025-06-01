package com.example.demo.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisponibilidadeDTO {

    private Long id;
    private Long medicoId;

    @NotNull(message = "É obrigatório informar o dia da semana")
    @Min(value = 0, message = "Dia da semana deve ser entre 0 (Domingo) e 6 (Sábado)")
    @Max(value = 6, message = "Dia da semana deve ser entre 0 (Domingo) e 6 (Sábado)")
    private Integer diaDaSemana;

    @NotNull(message = "É obrigatório informar o horário de início da consulta!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Schema(type = "string", pattern = "HH:mm:ss", example = "08:00:00")
    private LocalTime horarioInicio;

    @NotNull(message = "É obrigatório informar o horário de encerramento da consulta!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Schema(type = "string", pattern = "HH:mm:ss", example = "08:00:00")
    private LocalTime horarioFim;
}
