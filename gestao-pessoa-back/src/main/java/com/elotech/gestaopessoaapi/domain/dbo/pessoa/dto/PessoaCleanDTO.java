package com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto;

import java.time.LocalDate;

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

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

}