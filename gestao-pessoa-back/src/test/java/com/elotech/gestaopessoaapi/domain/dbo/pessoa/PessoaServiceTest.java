package com.elotech.gestaopessoaapi.domain.dbo.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import com.elotech.gestaopessoaapi.domain.dbo.contato.ContatoService;
import com.elotech.gestaopessoaapi.domain.dbo.contato.dto.ContatoDTO;
import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaFullDTO;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @Mock
    private PessoaRepository repository;

    @Mock
    private ContatoService contatoService;

    @InjectMocks
    private PessoaService pessoaService;

    @Service
    public class PessoaService {

        private final PessoaRepository repository;
        private final ContatoService contatoService;
        private final PessoaMapper mapper;

        public PessoaService(PessoaRepository repository, ContatoService contatoService, PessoaMapper mapper) {
            this.repository = repository;
            this.contatoService = contatoService;
            this.mapper = mapper;
        }

        // Outros métodos da classe PessoaService...
    }

    @Test
    void findById_ShouldReturnPessoa_WhenIdExists() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
        pessoa.setNome("Teste");
        pessoa.setCpf("12345678900");
        pessoa.setDataNascimento(LocalDate.of(2000, 1, 1));

        when(repository.findById(1)).thenReturn(Optional.of(pessoa));

        Pessoa result = pessoaService.findById(1);

        assertEquals(pessoa, result);
    }

    @Test
    void findById_ShouldThrowRuntimeException_WhenIdDoesNotExist() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> pessoaService.findById(1));
    }

    @Test
    void create_ShouldCreatePessoaAndContatos() {
        // Mockando o mapper
        PessoaMapper mapper = Mockito.mock(PessoaMapper.class);
        when(mapper.toPessoaFullDTO(any(Pessoa.class))).thenReturn(new PessoaFullDTO());

        // Criando instância da PessoaService com o mapper mockado
        PessoaService pessoaService = new PessoaService(repository, contatoService, mapper);

        // Seu teste continua aqui...
        PessoaFullDTO dto = new PessoaFullDTO();
        dto.setNome("Teste");
        dto.setCpf("12345678900");
        dto.setDataNascimento(LocalDate.of(2000, 1, 1));

        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setNome("Contato");
        contatoDTO.setEmail("contato@teste.com");
        contatoDTO.setTelefone("123456789");

        dto.setContatos(Collections.singletonList(contatoDTO));

        when(repository.save(any(Pessoa.class))).thenAnswer(invocation -> {
            Pessoa pessoa = invocation.getArgument(0);
            pessoa.setId(1);
            return pessoa;
        });

        PessoaFullDTO result = pessoaService.create(dto);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(contatoService, times(1)).createEntityList(eq(1), anyList());
    }

    @Test
    void update_ShouldUpdatePessoaAndContatos() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
        pessoa.setNome("Teste");
        pessoa.setCpf("12345678900");
        pessoa.setDataNascimento(LocalDate.of(2000, 1, 1));

        PessoaFullDTO dto = new PessoaFullDTO();
        dto.setNome("Teste Atualizado");
        dto.setCpf("98765432100");
        dto.setDataNascimento(LocalDate.of(1990, 1, 1));

        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setNome("Novo Contato");
        contatoDTO.setEmail("novocontato@teste.com");
        contatoDTO.setTelefone("987654321");

        dto.setContatos(Collections.singletonList(contatoDTO));

        when(repository.findById(1)).thenReturn(Optional.of(pessoa));

        PessoaFullDTO result = pessoaService.update(1, dto);

        assertNotNull(result);
        assertEquals(dto.getNome(), result.getNome());
        assertEquals(dto.getCpf(), result.getCpf());
        assertEquals(dto.getDataNascimento(), result.getDataNascimento());
        verify(contatoService, times(1)).updateEntityList(anyList());
    }

    @Test
    void delete_ShouldDeletePessoaAndContatos() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);

        when(repository.findById(1)).thenReturn(Optional.of(pessoa));

        pessoaService.delete(1);

        verify(contatoService, times(1)).deleteEntityList(anyList());
        verify(repository, times(1)).delete(eq(pessoa));
    }

    // Add more test cases as needed...
}