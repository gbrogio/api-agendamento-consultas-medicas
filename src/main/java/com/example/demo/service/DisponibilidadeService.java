package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Disponibilidade;
import com.example.demo.dto.DisponibilidadeDTO;
import com.example.demo.mapper.DisponibilidadeMapper;
import com.example.demo.repository.IDisponibilidadeRepository;
import com.example.demo.repository.IMedicoRepository;

@Service
public class DisponibilidadeService {

    @Autowired
    private DisponibilidadeMapper disponibilidadeMapper;

    @Autowired
    private IDisponibilidadeRepository disponibilidadeRepository;

    @Autowired
    private IMedicoRepository medicoRepository;

    public DisponibilidadeDTO registrarDisponibilidade(Long medicoId, DisponibilidadeDTO disponibilidadeDTO) {
        Disponibilidade disponibilidade = disponibilidadeMapper.toEntity(disponibilidadeDTO);
        disponibilidade.setMedico(medicoRepository.findById(medicoId)
                .orElseThrow(() -> new IllegalArgumentException("Medico não encontrado com o ID: " + medicoId)));
        return disponibilidadeMapper.toDTO(disponibilidadeRepository.save(disponibilidade));
    }

    public List<DisponibilidadeDTO> listarDisponibilidade(Long medicoId) {
        return disponibilidadeMapper.toDTOList(disponibilidadeRepository.findByMedicoId(medicoId));
    }

    public void removerDisponibilidade(Long Id) {
        disponibilidadeRepository.deleteById(Id);
    }
}