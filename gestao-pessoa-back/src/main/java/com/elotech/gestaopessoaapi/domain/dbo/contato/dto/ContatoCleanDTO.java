package com.elotech.gestaopessoaapi.domain.dbo.contato.dto;

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
public class ContatoCleanDTO {

    private Integer id;

    private String nome;

    private String telefone;

    private String email;
}
