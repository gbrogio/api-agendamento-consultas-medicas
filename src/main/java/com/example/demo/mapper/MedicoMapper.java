package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.Entities.Medico;
import com.example.demo.dto.MedicoDTO;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    @Mapping(source = "especialidade.id", target = "especialidadeId")
    MedicoDTO toDTO(Medico medico);

    @Mapping(target = "especialidade.id", source = "especialidadeId")
    @Mapping(target = "consultas", ignore = true)
    @Mapping(target = "disponibilidades", ignore = true)
    Medico toEntity(MedicoDTO medicoDTO);

    List<MedicoDTO> toDTOList(List<Medico> medicos);
}
