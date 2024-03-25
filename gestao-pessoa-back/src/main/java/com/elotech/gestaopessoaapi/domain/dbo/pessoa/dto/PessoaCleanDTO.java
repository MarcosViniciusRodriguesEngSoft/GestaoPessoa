package com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaCleanDTO {

    private Integer id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

}