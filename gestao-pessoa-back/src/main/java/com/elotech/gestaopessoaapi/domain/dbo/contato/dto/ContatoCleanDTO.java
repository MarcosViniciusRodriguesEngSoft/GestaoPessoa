package com.elotech.gestaopessoaapi.domain.dbo.contato.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @NotBlank
    private String email;
}
