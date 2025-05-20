package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.Entities.Paciente;
import com.example.demo.dto.PacienteDTO;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteDTO toDTO(Paciente paciente);

    Paciente toEntity(PacienteDTO pacienteDTO);

    List<PacienteDTO> toDTOList(List<Paciente> pacientes);
}
