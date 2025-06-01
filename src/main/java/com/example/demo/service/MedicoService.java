package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Especialidade;
import com.example.demo.Entities.Medico;
import com.example.demo.dto.MedicoDTO;
import com.example.demo.mapper.MedicoMapper;
import com.example.demo.repository.IMedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private IMedicoRepository medicoRepository;

    @Autowired
    private MedicoMapper medicoMapper;

    public List<MedicoDTO> listarTodos() {
        return medicoMapper.toDTOList(medicoRepository.findAll());
    }

    public Optional<MedicoDTO> buscarPorId(Long id) {
        Optional<MedicoDTO> medicoDTO = medicoRepository.findById(id).map(medicoMapper::toDTO);
        return medicoDTO;

    }

    public MedicoDTO salvar(MedicoDTO medicoDTO) {
        Medico medico = medicoMapper.toEntity(medicoDTO);
        return medicoMapper.toDTO(medicoRepository.save(medico));
    }

    public MedicoDTO atualizar(Long id, MedicoDTO medicoDTO) {
        return medicoRepository.findById(id)
                .map(medico -> {
                    medico.setNome(medicoDTO.getNome());
                    medico.setCrm(medicoDTO.getCrm());
                    Especialidade especialidade = new Especialidade();
                    especialidade.setId(medicoDTO.getEspecialidadeId());
                    medico.setEspecialidade(especialidade);
                    medicoRepository.save(medico);
                    return medicoMapper.toDTO(medico);
                })
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado com o ID: " + id));
    }

    public MedicoDTO deletar(Long id) {
        return medicoRepository.findById(id)
                .map(medico -> {
                    medico.setAtivo(false);
                    medicoRepository.save(medico);
                    return medicoMapper.toDTO(medico);
                })
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado com o ID: " + id));
    }

    public MedicoDTO reativar(Long id) {
        return medicoRepository.findById(id)
                .map(medico -> {
                    medico.setAtivo(true);
                    medicoRepository.save(medico);
                    return medicoMapper.toDTO(medico);
                })
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado com o ID: " + id));
    }
}
