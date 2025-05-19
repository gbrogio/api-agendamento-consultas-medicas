package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Disponibilidade;
import com.example.demo.dto.DisponibilidadeDTO;
import com.example.demo.mapper.DisponibildadeMapper;
import com.example.demo.repository.IDisponibilidadeRepository;

@Service
public class DisponibilidadeService {

    @Autowired
    private DisponibildadeMapper disponibilidadeMapper;
    
    @Autowired
    private IDisponibilidadeRepository disponibilidadeRepository;

    public DisponibilidadeDTO registrarDisponibilidade(Long medicoId, DisponibilidadeDTO disponibilidadeDTO){
        /*Disponibilidade disponibilidade = disponibilidadeMapper.toEntity(medicoId, disponibilidadeDTO.getDiaDaSemana(),
        disponibilidadeDTO.getHorarioInicio(), disponibilidadeDTO.getHorarioFim());*/
        Disponibilidade disponibilidade = disponibilidadeMapper.toEntity(disponibilidadeDTO);
        return disponibilidadeMapper.toDTO(disponibilidadeRepository.save(disponibilidade));
    }

    public List<DisponibilidadeDTO> listarDisponibilidade(Long medicoId){
        return  disponibilidadeMapper.toDTOList(disponibilidadeRepository.findByMedicoId(medicoId));
    }

    public void removerDisponibilidade(Long Id){
        disponibilidadeRepository.deleteById(Id);
    }
}