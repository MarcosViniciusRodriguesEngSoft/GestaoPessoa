package com.elotech.gestaopessoaapi.domain.dbo.contato;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.elotech.gestaopessoaapi.domain.dbo.contato.dto.ContatoCleanDTO;

@SpringBootTest
class ContatoServiceTest {

    @Autowired
    private ContatoService contatoService;

    @MockBean
    private ContatoRepository repository;

    @MockBean
    private ContatoMapper mapper;

    @Test
    void testCreateEntityList() {
        // Mocking data
        List<ContatoCleanDTO> contatos = new ArrayList<>();
        contatos.add(new ContatoCleanDTO(1, "Maria", "987654321", "maria@example.com"));
        contatos.add(new ContatoCleanDTO(2, "Pedro", "123456789", "pedro@example.com"));

        // Simulando o comportamento do repository ao salvar os contatos
        when(repository.save(any(Contato.class))).thenAnswer(invocation -> {
            Contato savedContato = invocation.getArgument(0);
            savedContato.setId(1); // Simulando a geração de um ID
            return savedContato;
        });

        // Simulando o comportamento do mapper
        when(mapper.toEntity(any(ContatoCleanDTO.class))).thenAnswer(invocation -> {
            ContatoCleanDTO dto = invocation.getArgument(0);
            Contato contato = new Contato();
            contato.setId(dto.getId());
            contato.setNome(dto.getNome());
            contato.setTelefone(dto.getTelefone());
            contato.setEmail(dto.getEmail());
            return contato;
        });

        // Simulando o comportamento do mapper ao converter de entidade para DTO
        when(mapper.toContatoCleanDTO(any(Contato.class))).thenAnswer(invocation -> {
            Contato contato = invocation.getArgument(0);
            ContatoCleanDTO dto = new ContatoCleanDTO();
            dto.setId(contato.getId());
            dto.setNome(contato.getNome());
            dto.setTelefone(contato.getTelefone());
            dto.setEmail(contato.getEmail());
            return dto;
        });

        // Testando o método createEntityList
        List<Contato> entityList = contatoService.createEntityList(1, contatos);

        // Verifica se a lista retornada não é nula
        assertNotNull(entityList);
        // Verifica se o tamanho da lista está correto
        assertEquals(2, entityList.size());
        // Verifica se os IDs dos contatos foram atribuídos corretamente
        assertEquals(1, entityList.get(0).getId());
        assertEquals(1, entityList.get(1).getId());
    }
}
