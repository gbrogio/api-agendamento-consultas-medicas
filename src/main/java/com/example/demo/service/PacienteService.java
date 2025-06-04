package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Paciente;
import com.example.demo.dto.PacienteDTO;
import com.example.demo.mapper.PacienteMapper;
import com.example.demo.repository.IPacienteRepository;

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
        Optional<PacienteDTO> pacienteDTO = pacienteRepository.findById(id).map(pacienteMapper::toDTO);
        return pacienteDTO;
    }

    public PacienteDTO salvar(PacienteDTO pacienteDTO) {
        Paciente paciente = pacienteMapper.toEntity(pacienteDTO);
        return pacienteMapper.toDTO(pacienteRepository.save(paciente));
    }

    public PacienteDTO atualizar(long id, PacienteDTO pacienteDTO) {
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setNome(pacienteDTO.getNome());
            paciente.setCpf(pacienteDTO.getCpf());
            paciente.setEmail(pacienteDTO.getEmail());
            paciente.setEmail(pacienteDTO.getTelefone());
            pacienteRepository.save(paciente);
            return pacienteMapper.toDTO(paciente);
        }).orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com o ID: " + id));
    }

    public PacienteDTO inativar(Long id) {
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setAtivo(false);
            pacienteRepository.save(paciente);
            return pacienteMapper.toDTO(paciente);
        }).orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com o ID: " + id));
    }

    public PacienteDTO reativar(Long id) {
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setAtivo(true);
            pacienteRepository.save(paciente);
            return pacienteMapper.toDTO(paciente);
        }).orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado com o ID: " + id));
    }

}
