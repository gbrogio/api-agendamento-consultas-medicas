package com.example.demo.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultaDTO {

    @Hidden
    private Long id;

    @NotNull(message = "O ID do paciente é obrigatório")
    private Long pacienteId;

    @Hidden
    private String nomePaciente;

    @NotNull(message = "O ID do médico é obrigatório")
    private Long medicoId;

    @Hidden
    private String nomeMedico;

    @NotNull(message = "A data e hora da consulta são obrigatórias")
    @Future(message = "A data e hora da consulta devem ser futuras")
    private LocalDateTime dataHora;

    @Hidden
    private String status;

    @Hidden
    private String observacoes;
}
