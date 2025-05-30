package com.example.demo.Entities;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "Disponibildade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Disponibilidade {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private Long medicoId;

   @Column(nullable = false)
   private Integer diaDaSemana;

   @Column(nullable = false)
   @DateTimeFormat(pattern = "HH:mm:ss")
   private LocalTime horarioInicio;

   @Column(nullable = false)
   @DateTimeFormat(pattern = "HH:mm:ss")
   private LocalTime horarioFim;

}
