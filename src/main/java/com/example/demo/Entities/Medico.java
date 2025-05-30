package com.example.demo.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Esse trecho do código é a entidade Medico, que representa a tabela Medico no banco de dados
// Ela possui os atributos id, nome, crm e especialidadeId, que são mapeados para as colunas da tabela

@Data
@Entity
@Table(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String crm;

    @Column(name = "especialidade_id", nullable = false)
    private Long especialidadeId;

    @Column(nullable = false)
    private boolean ativo = true;

}
