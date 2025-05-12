package com.example.demo.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entities.Disponibilidade;
import com.example.demo.repository.IDisponibilidadeRepository;

@Service
public class DisponibilidadeService {
    private final IDisponibilidadeRepository disponibilidadeRepository;

    public DisponibilidadeService(IDisponibilidadeRepository disponibilidadeRepository){
        this.disponibilidadeRepository = disponibilidadeRepository;
    }

    public Disponibilidade registrarDisponibilidade(Long medicoId, String diaDaSemana, LocalTime horarioInicio, LocalTime horarioFim){
        Disponibilidade disponibilidade = new Disponibilidade(medicoId, diaDaSemana,horarioInicio, horarioFim);
        return disponibilidadeRepository.save(disponibilidade);
    }

    public List<Disponibilidade> listarDisponibilidade(Long medicoId){
        return disponibilidadeRepository.findByMedicoId(medicoId);
    }

    public void removerDisponibilidade(Long Id){
        disponibilidadeRepository.deleteById(Id);
    }
}