package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.Entities.*;
import com.example.demo.dto.EspecialidadeDTO;

@Mapper(componentModel = "spring")
public interface EspecialidadeMapper {
    EspecialidadeDTO toDTO(Especialidade especialidade);

    Especialidade toEntity(EspecialidadeDTO especialidadeDTO);

    List<EspecialidadeDTO> toDTOList(List<Especialidade> especialidades);
}
