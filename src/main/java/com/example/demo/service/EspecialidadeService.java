package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entities.Especialidade;
import com.example.demo.dto.EspecialidadeDTO;
import com.example.demo.mapper.EspecialidadeMapper;
import com.example.demo.repository.IEspecialidadeRepository;

@Service
public class EspecialidadeService {

    @Autowired
    private IEspecialidadeRepository especialidadeRepository;

    @Autowired
    private EspecialidadeMapper especialidadeMapper;

    public List<EspecialidadeDTO> listarTodos() {
        return especialidadeMapper.toDTOList(especialidadeRepository.findAll());
    }

    public Optional<EspecialidadeDTO> buscarPorId(Long id) {
        return especialidadeRepository.findById(id).map(especialidadeMapper::toDTO);
    }

    public EspecialidadeDTO salvar(EspecialidadeDTO especialidadeDTO) {
        Especialidade especialidade = especialidadeMapper.toEntity(especialidadeDTO);
        return especialidadeMapper.toDTO(especialidadeRepository.save(especialidade));
    }

    public void deletar(Long id) {
        especialidadeRepository.deleteById(id);
    }
}
