package com.elotech.gestaopessoaapi.domain.dbo.contato.dto;

import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ContatoDTO extends ContatoCleanDTO {

    private PessoaDTO pessoa;

}
