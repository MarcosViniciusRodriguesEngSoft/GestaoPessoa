package com.elotech.gestaopessoaapi.domain.dbo.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.elotech.gestaopessoaapi.domain.dbo.contato.Contato;
import com.elotech.gestaopessoaapi.domain.dbo.contato.ContatoService;
import com.elotech.gestaopessoaapi.domain.dbo.contato.dto.ContatoCleanDTO;
import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaFullDTO;

@SpringBootTest
public class PessoaServiceTest {

    @Autowired
    private PessoaService pessoaService;

    @MockBean
    private PessoaRepository pessoaRepository;

    @MockBean
    private ContatoService contatoService;

    @Test
    public void testCreate() {
        // Mock de dados de entrada
        PessoaFullDTO dtoEntrada = new PessoaFullDTO();
        dtoEntrada.setNome("Teste");
        dtoEntrada.setCpf("12345678900");
        dtoEntrada.setDataNascimento(LocalDate.of(1990, 1, 1));

        ContatoCleanDTO contatoDTO = new ContatoCleanDTO();
        contatoDTO.setNome("Contato de Teste");
        List<ContatoCleanDTO> contatos = new ArrayList<>();
        contatos.add(contatoDTO);
        dtoEntrada.setContatos(contatos);

        // Mock do retorno do repository e do serviço de contato
        Pessoa pessoaMock = new Pessoa();
        pessoaMock.setId(1);
        when(pessoaRepository.save(any())).thenReturn(pessoaMock);

        Contato contatoMock = new Contato();
        contatoMock.setId(1);
        when(contatoService.createEntityList(any())).thenReturn(List.of(contatoMock));

        // Execução do método a ser testado
        PessoaFullDTO resultado = pessoaService.create(dtoEntrada);

        // Verificações dos mocks e do resultado
        assertNotNull(resultado);
        assertEquals("Teste", resultado.getNome());
        assertEquals("12345678900", resultado.getCpf());
        assertEquals(LocalDate.of(1990, 1, 1), resultado.getDataNascimento());
        assertEquals(1, resultado.getContatos().size());
        assertEquals(1, resultado.getContatos().get(0).getId());

        verify(pessoaRepository, times(1)).save(any());
        verify(contatoService, times(1)).createEntityList(any());
    }
}
