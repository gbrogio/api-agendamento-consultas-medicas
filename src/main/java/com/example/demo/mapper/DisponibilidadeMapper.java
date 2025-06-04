package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.Entities.Disponibilidade;
import com.example.demo.dto.DisponibilidadeDTO;

@Mapper(componentModel = "spring")
public interface DisponibilidadeMapper {
    @Mapping(source = "medico.id", target = "medicoId")
    DisponibilidadeDTO toDTO(Disponibilidade disponibilidade);

    @Mapping(source = "medicoId", target = "medico.id")
    Disponibilidade toEntity(DisponibilidadeDTO disponibilidadeDTO);

    List<DisponibilidadeDTO> toDTOList(List<Disponibilidade> disponibilidades);
}
