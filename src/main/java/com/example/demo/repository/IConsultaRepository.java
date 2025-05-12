package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Consulta;
import com.example.demo.Entities.Medico;
import com.example.demo.Entities.Paciente;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Long> {
    Optional<Consulta> findById(Long id);
    
    List<Consulta> findByPaciente(Paciente paciente);
    
    List<Consulta> findByMedico(Medico medico);
    
    // Manter também métodos compatíveis com busca por ID diretamente
    @Query("SELECT c FROM Consulta c WHERE c.paciente.id = :pacienteId")
    List<Consulta> findByPacienteId(@Param("pacienteId") Long pacienteId);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico.id = :medicoId")
    List<Consulta> findByMedicoId(@Param("medicoId") Long medicoId);
    
    List<Consulta> findByStatus(String status);
    
    List<Consulta> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
    
    @Query("SELECT c FROM Consulta c WHERE c.medico.id = :medicoId AND c.dataHora BETWEEN :inicio AND :fim")
    List<Consulta> findByMedicoIdAndDataHoraBetween(
        @Param("medicoId") Long medicoId, 
        @Param("inicio") LocalDateTime inicio, 
        @Param("fim") LocalDateTime fim
    );
    
    @Query("SELECT c FROM Consulta c WHERE c.paciente.id = :pacienteId AND c.status = :status")
    List<Consulta> findByPacienteIdAndStatus(
        @Param("pacienteId") Long pacienteId, 
        @Param("status") String status
    );
}
