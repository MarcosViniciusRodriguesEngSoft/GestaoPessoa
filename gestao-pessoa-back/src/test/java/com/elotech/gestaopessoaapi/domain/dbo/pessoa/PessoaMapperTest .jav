package com.elotech.gestaopessoaapi.domain.dbo.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaCleanDTO;
import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaFullDTO;

class PessoaMapperTest {

    private PessoaMapper mapper;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = mock(ModelMapper.class);
        mapper = new PessoaMapper(modelMapper);
    }

    @Test
    void testToPessoaFullDTO() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
        pessoa.setNome("Maria");
        pessoa.setEmail("maria@example.com");

        PessoaFullDTO expectedDto = new PessoaFullDTO();
        expectedDto.setId(1);
        expectedDto.setNome("Maria");
        expectedDto.setEmail("maria@example.com");

        when(modelMapper.map(pessoa, PessoaFullDTO.class)).thenReturn(expectedDto);

        PessoaFullDTO resultDto = mapper.toPessoaFullDTO(pessoa);

        assertEquals(expectedDto.getId(), resultDto.getId());
        assertEquals(expectedDto.getNome(), resultDto.getNome());
        assertEquals(expectedDto.getEmail(), resultDto.getEmail());
    }

    @Test
    void testToCleanDTO() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(2);
        pessoa.setNome("João");
        pessoa.setEmail("joao@example.com");

        PessoaCleanDTO expectedDto = new PessoaCleanDTO();
        expectedDto.setId(2);
        expectedDto.setNome("João");
        expectedDto.setEmail("joao@example.com");

        when(modelMapper.map(pessoa, PessoaCleanDTO.class)).thenReturn(expectedDto);

        PessoaCleanDTO resultDto = mapper.toCleanDTO(pessoa);

        assertEquals(expectedDto.getId(), resultDto.getId());
        assertEquals(expectedDto.getNome(), resultDto.getNome());
        assertEquals(expectedDto.getEmail(), resultDto.getEmail());
    }

    @Test
    void testToCleanDTOList() {
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(1);
        pessoa1.setNome("Maria");
        pessoa1.setEmail("maria@example.com");

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setId(2);
        pessoa2.setNome("João");
        pessoa2.setEmail("joao@example.com");

        List<Pessoa> pessoas = Arrays.asList(pessoa1, pessoa2);

        PessoaCleanDTO expectedDto1 = new PessoaCleanDTO();
        expectedDto1.setId(1);
        expectedDto1.setNome("Maria");
        expectedDto1.setEmail("maria@example.com");

        PessoaCleanDTO expectedDto2 = new PessoaCleanDTO();
        expectedDto2.setId(2);
        expectedDto2.setNome("João");
        expectedDto2.setEmail("joao@example.com");

        when(modelMapper.map(pessoa1, PessoaCleanDTO.class)).thenReturn(expectedDto1);
        when(modelMapper.map(pessoa2, PessoaCleanDTO.class)).thenReturn(expectedDto2);

        List<PessoaCleanDTO> resultDtoList = mapper.toCleanDTO(pessoas);

        assertEquals(2, resultDtoList.size());
        assertEquals(expectedDto1.getId(), resultDtoList.get(0).getId());
        assertEquals(expectedDto1.getNome(), resultDtoList.get(0).getNome());
        assertEquals(expectedDto1.getEmail(), resultDtoList.get(0).getEmail());
        assertEquals(expectedDto2.getId(), resultDtoList.get(1).getId());
        assertEquals(expectedDto2.getNome(), resultDtoList.get(1).getNome());
        assertEquals(expectedDto2.getEmail(), resultDtoList.get(1).getEmail());
    }
}
