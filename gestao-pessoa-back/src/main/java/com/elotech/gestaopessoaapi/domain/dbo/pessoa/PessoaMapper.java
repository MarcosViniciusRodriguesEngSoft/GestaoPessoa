package com.elotech.gestaopessoaapi.domain.dbo.pessoa;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaCleanDTO;
import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaFullDTO;

@Component
public class PessoaMapper {

    private final ModelMapper modelMapper;

    public PessoaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PessoaFullDTO toPessoaFullDTO(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaFullDTO.class);
    }

    public PessoaCleanDTO toCleanDTO(Pessoa entity) {
        return entity != null ? this.modelMapper.map(entity, PessoaCleanDTO.class) : null;
    }

    public List<PessoaCleanDTO> toCleanDTO(List<Pessoa> entities) {
        return entities.stream().map(this::toCleanDTO).collect(Collectors.toList());
    }
}