package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Medico;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByNome(String nome);

    Optional<Medico> findByCrm(String crm);

    List<Medico> findByEspecialidadeId(Long especialidadeId);
}

// esse trecho do codigo é o repositório do Medico, que estende a interface
// JpaRepository
// ele possui métodos para buscar médicos por id, nome, crm e especialidadeId