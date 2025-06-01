package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.Entities.*;
import com.example.demo.dto.DisponibilidadeDTO;

@Mapper(componentModel = "spring")
public interface DisponibildadeMapper {
    DisponibilidadeDTO toDTO(Disponibilidade disponibilidade);

    Disponibilidade toEntity(DisponibilidadeDTO disponibilidadeDTO);

    List<DisponibilidadeDTO> toDTOList(List<Disponibilidade> disponibilidades);
}
