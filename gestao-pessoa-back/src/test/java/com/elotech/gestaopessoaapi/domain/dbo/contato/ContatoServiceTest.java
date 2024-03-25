import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.elotech.gestaopessoaapi.domain.dbo.contato.dto.ContatoCleanDTO;

class ContatoServiceTest {

    @Mock
    private ContatoRepository repository;

    @Mock
    private ContatoMapper mapper;

    @InjectMocks
    private ContatoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdate() {
        // Mocking data
        ContatoCleanDTO dto = new ContatoCleanDTO();
        dto.setNome("João");
        dto.setTelefone("123456789");
        dto.setEmail("joao@example.com");

        Contato contato = new Contato();
        contato.setId(1);
        contato.setNome("Antônio");
        contato.setTelefone("987654321");
        contato.setEmail("antonio@example.com");

        when(repository.findById(1)).thenReturn(Optional.of(contato));
        when(repository.save(any(Contato.class))).thenReturn(contato);
        when(mapper.toContatoCleanDTO(any(Contato.class))).thenReturn(dto);

        // Testando o método update
        ContatoCleanDTO updatedDTO = service.update(1, dto);

        // Verifica se o DTO retornado não é nulo
        assertNotNull(updatedDTO);
        // Verifica se os valores foram atualizados corretamente
        assertEquals("João", updatedDTO.getNome());
        assertEquals("123456789", updatedDTO.getTelefone());
        assertEquals("joao@example.com", updatedDTO.getEmail());
    }

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

        // Testando o método createEntityList
        List<Contato> entityList = service.createEntityList(1, contatos);

        // Verifica se a lista retornada não é nula
        assertNotNull(entityList);
        // Verifica se o tamanho da lista está correto
        assertEquals(2, entityList.size());
        // Verifica se os IDs dos contatos foram atribuídos corretamente
        assertEquals(1, entityList.get(0).getId());
        assertEquals(1, entityList.get(1).getId());
    }

    // Adicione mais testes para os outros métodos conforme necessário
}
