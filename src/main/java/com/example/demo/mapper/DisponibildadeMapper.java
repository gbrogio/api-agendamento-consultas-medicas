package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.demo.Entities.Disponibilidade;
import com.example.demo.dto.DisponibilidadeDTO;

<<<<<<< HEAD

@Mapper(componentModel = "spring")
public interface DisponibildadeMapper {
    DisponibilidadeDTO toDTO(Disponibilidade disponibilidade);
    Disponibilidade toEntity(DisponibilidadeDTO disponibilidadeDTO);
=======
@Mapper(componentModel = "spring")
public interface DisponibildadeMapper {
    DisponibilidadeDTO toDTO(Disponibilidade disponibilidade);

    Disponibilidade toEntity(DisponibilidadeDTO disponibilidadeDTO);

>>>>>>> homolog
    List<DisponibilidadeDTO> toDTOList(List<Disponibilidade> disponibilidades);
}
