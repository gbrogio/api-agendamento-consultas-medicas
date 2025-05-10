package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Paciente;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByid(Long id);
    Optional<Paciente> findByNome(String Nome);
    Optional<Paciente> findByCpf(String Cpf);
    Optional<Paciente> findByEmail(String Email);
    Optional<Paciente> findByTelefone(String Telefone);
    Optional<Paciente> findByDataNascimento(LocalDate DataNascimento);
}