package com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto;

import java.util.List;

import com.elotech.gestaopessoaapi.domain.dbo.contato.dto.ContatoCleanDTO;

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
public class PessoaFullDTO extends PessoaDTO {

    private List<ContatoCleanDTO> contatos;
}
