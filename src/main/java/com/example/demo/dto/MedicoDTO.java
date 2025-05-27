package com.example.demo.dto;

//import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

// Class feita para a validação dos atributos da classe Médico

@Data
@NoArgsConstructor
public class MedicoDTO {

    private Long id;
    private boolean ativo = true;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @NotBlank(message = "O CRM é obrigatório")
    @Size(min = 5, max = 10, message = "O CRM deve ter entre 5 e 10 caracteres")
    private String crm;

    @NotBlank(message = "A especialidade é obrigatória")
    @Size(min = 3, max = 50, message = "A especialidade deve ter entre 3 e 50 caracteres")
    private String especialidadeId;
}
