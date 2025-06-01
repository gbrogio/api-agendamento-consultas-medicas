package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.Entities.*;
import com.example.demo.dto.ConsultaDTO;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {

    @Mapping(source = "paciente.id", target = "pacienteId")
    @Mapping(source = "medico.id", target = "medicoId")
    @Mapping(source = "paciente.nome", target = "nomePaciente")
    @Mapping(source = "medico.nome", target = "nomeMedico")
    ConsultaDTO toDTO(Consulta consulta);

    @Mapping(target = "paciente.id", source = "pacienteId")
    @Mapping(target = "medico.id", source = "medicoId")
    Consulta toEntity(ConsultaDTO consultaDTO);

    List<ConsultaDTO> toDTOList(List<Consulta> consultas);
}
