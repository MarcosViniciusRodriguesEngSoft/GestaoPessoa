package com.elotech.gestaopessoaapi.domain.dbo.contato.dto;

import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ContatoDTO extends ContatoCleanDTO {

    private PessoaDTO pessoa;

}
