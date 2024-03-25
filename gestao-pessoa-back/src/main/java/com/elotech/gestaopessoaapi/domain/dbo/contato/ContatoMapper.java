package com.elotech.gestaopessoaapi.domain.dbo.contato;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.elotech.gestaopessoaapi.domain.dbo.contato.dto.ContatoCleanDTO;
import com.elotech.gestaopessoaapi.domain.dbo.contato.dto.ContatoDTO;
import com.elotech.gestaopessoaapi.domain.dbo.contato.dto.ContatoFullDTO;


@Component
public class ContatoMapper {

    private final ModelMapper modelMapper;

    public ContatoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ContatoCleanDTO toContatoCleanDTO(Contato contato) {
        return modelMapper.map(contato, ContatoCleanDTO.class);
    }

    public ContatoDTO toContatoDTO(Contato contato) {
        return modelMapper.map(contato, ContatoDTO.class);
    }

    public ContatoFullDTO toContatoFullDTO(Contato contato) {
        return modelMapper.map(contato, ContatoFullDTO.class);
    }

    public Contato toEntity(ContatoDTO contatoDTO) {
        return modelMapper.map(contatoDTO, Contato.class);
    }

    public Contato toEntity(ContatoCleanDTO contatoCleanDTO) {
        return modelMapper.map(contatoCleanDTO, Contato.class);
    }

    public Contato toEntity(ContatoFullDTO contatoFullDTO) {
        return modelMapper.map(contatoFullDTO, Contato.class);
    }
}