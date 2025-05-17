package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entities.Disponibilidade;
import com.example.demo.dto.DisponibilidadeDTO;
import com.example.demo.repository.IDisponibilidadeRepository;

@Service
public class DisponibilidadeService {
    private final IDisponibilidadeRepository disponibilidadeRepository;

    public DisponibilidadeService(IDisponibilidadeRepository disponibilidadeRepository){
        this.disponibilidadeRepository = disponibilidadeRepository;
    }

    public DisponibilidadeDTO registrarDisponibilidade(Long medicoId, DisponibilidadeDTO disponibilidadeDTO){
        Disponibilidade disponibilidade = new Disponibilidade(medicoId, disponibilidadeDTO.getDiaDaSemana(),
        disponibilidadeDTO.getHorarioInicio(), disponibilidadeDTO.getHorarioFim());
        
        return Disponibilidade.toDTO(disponibilidadeRepository.save(disponibilidadeDTO));
        // return disponibilidadeRepository.save(disponibilidade);
    }

    public List<Disponibilidade> listarDisponibilidade(Long medicoId){
        return  disponibilidadeRepository.findByMedicoId(medicoId);
    }

    public void removerDisponibilidade(Long Id){
        disponibilidadeRepository.deleteById(Id);
    }
}