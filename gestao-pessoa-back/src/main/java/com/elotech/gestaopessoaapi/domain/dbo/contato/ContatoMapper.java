package com.elotech.gestaopessoaapi.domain.dbo.contato;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.elotech.gestaopessoaapi.domain.dbo.contato.dto.ContatoCleanDTO;

@Component
public class ContatoMapper {

    private final ModelMapper modelMapper;

    public ContatoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ContatoCleanDTO toContatoCleanDTO(Contato contato) {
        return modelMapper.map(contato, ContatoCleanDTO.class);
    }

    public Contato toEntity(ContatoCleanDTO contatoCleanDTO) {
        return modelMapper.map(contatoCleanDTO, Contato.class);
    }

}