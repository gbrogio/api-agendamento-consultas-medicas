package com.example.demo.service;

import com.example.demo.Entities.Paciente;
import com.example.demo.dto.PacienteDTO;
import com.example.demo.mapper.PacienteMapper;
import com.example.demo.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    private PacienteMapper pacienteMapper;
    

    public List<PacienteDTO> listarTodos() {
        return pacienteMapper.toDTOList(pacienteRepository.findAll());
    }

    public Optional<PacienteDTO> buscarPorId(Long id) {
        return pacienteRepository.findById(id).map(pacienteMapper::toDTO);
    }

    public PacienteDTO salvar(PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteMapper.toEntity(pacienteDTO);
        return pacienteMapper.toDTO(pacienteRepository.save(paciente));
    }

    public PacienteDTO atualizar(Long id, PacienteDTO pacienteDTO) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            paciente.setNome(pacienteDTO.getNome());
            paciente.setCpf(pacienteDTO.getCpf());
            paciente.setEmail(pacienteDTO.getEmail());
            paciente.setTelefone(pacienteDTO.getTelefone());
            return pacienteMapper.toDTO(pacienteRepository.save(paciente));
        } else {
            throw new IllegalArgumentException("Paciente não encontrado com o ID: " + id);
        }
    }

    public void deletar(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            paciente.get().setAtivo(false); // Desativa o paciente
            pacienteRepository.save(paciente.get()); // Salva a alteração
        } else {
            throw new IllegalArgumentException("Paciente não encontrado com o ID: " + id);
        }
    }
}