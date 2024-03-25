package com.elotech.gestaopessoaapi.domain.dbo.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.elotech.gestaopessoaapi.domain.dbo.contato.ContatoService;
import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaFullDTO;
import com.elotech.gestaopessoaapi.exception.BadRequestException;

@SpringBootTest
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private PessoaMapper pessoaMapper;

    @Mock
    private ContatoService contatoService;

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    public void testCreateWithValidData() {
        // Mockando dados de entrada
        PessoaFullDTO dtoEntrada = new PessoaFullDTO();
        dtoEntrada.setNome("João da Silva");
        dtoEntrada.setCpf("12345678900");
        dtoEntrada.setDataNascimento(LocalDate.of(1980, 5, 15));

        // Mockando comportamento do repository
        Pessoa pessoaSalva = new Pessoa();
        pessoaSalva.setId(1);
        when(pessoaRepository.save(any())).thenReturn(pessoaSalva);

        // Mockando comportamento do serviço de contatos
        doNothing().when(contatoService).createEntityList(anyInt(), anyList());

        // Executando o método a ser testado
        PessoaFullDTO resultado = pessoaService.create(dtoEntrada);

        // Verificando o resultado
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("João da Silva", resultado.getNome());
        assertEquals("12345678900", resultado.getCpf());
        assertEquals(LocalDate.of(1980, 5, 15), resultado.getDataNascimento());
    }

    @Test
    public void testCreateWithEmptyContacts() {
        // Mockando dados de entrada com lista de contatos vazia
        PessoaFullDTO dtoEntrada = new PessoaFullDTO();
        dtoEntrada.setNome("Maria Souza");
        dtoEntrada.setCpf("98765432100");
        dtoEntrada.setDataNascimento(LocalDate.of(1995, 10, 20));
        dtoEntrada.setContatos(Collections.emptyList());

        // Executando o método a ser testado e verificando se uma exceção é lançada
        assertThrows(BadRequestException.class, () -> {
            pessoaService.create(dtoEntrada);
        });
    }
}