package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Consulta;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Long> {
        @Query("""
                        SELECT c FROM Consulta c
                        WHERE (:medicoId IS NULL OR c.medico.id = :medicoId)
                          AND (:pacienteId IS NULL OR c.paciente.id = :pacienteId)
                          AND (:status IS NULL OR c.status = :status)
                          AND (:dataInicio IS NULL OR c.dataHora >= :dataInicio)
                          AND (:dataFim IS NULL OR c.dataHora <= :dataFim)
                        """)
        List<Consulta> findAllUsing(@Param("medicoId") Long medicoId, @Param("pacienteId") Long pacienteId,
                        @Param("status") String status, @Param("dataInicio") LocalDateTime dataInicio,
                        @Param("dataFim") LocalDateTime dataFim);

        @Query("SELECT c FROM Consulta c WHERE c.medico.id = :medicoId AND c.dataHora BETWEEN :inicio AND :fim")
        List<Consulta> findByMedicoIdAndDataHoraBetween(@Param("medicoId") Long medicoId,
                        @Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}
