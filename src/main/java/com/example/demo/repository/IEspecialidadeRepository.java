package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Especialidade;
import java.util.Optional;


@Repository
public interface IEspecialidadeRepository extends JpaRepository<Especialidade, Long> {
    Optional<Especialidade> findById(Long id);

    Optional<Especialidade> findByNome(String nome);
}




