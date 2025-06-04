package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.Entities.Paciente;
import com.example.demo.dto.PacienteDTO;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteDTO toDTO(Paciente paciente);

    @Mapping(target = "consultas", ignore = true)
    Paciente toEntity(PacienteDTO pacienteDTO);

    List<PacienteDTO> toDTOList(List<Paciente> pacientes);
}
