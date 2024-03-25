package com.elotech.gestaopessoaapi.domain.dbo.contato;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.elotech.gestaopessoaapi.domain.dbo.contato.dto.ContatoCleanDTO;
import com.elotech.gestaopessoaapi.domain.dbo.pessoa.Pessoa;

import jakarta.transaction.Transactional;

@Service
public class ContatoService {

    private final ContatoRepository repository;
    private final ContatoMapper mapper;

    @Autowired
    public ContatoService(
            ContatoRepository repository,
            ContatoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ContatoCleanDTO update(Integer id, @RequestBody ContatoCleanDTO dto) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado com o ID: " + id));

        contato.setNome(dto.getNome());
        contato.setTelefone(dto.getTelefone());
        contato.setEmail(dto.getEmail());

        var entity = repository.save(contato);

        return mapper.toContatoCleanDTO(entity);
    }

    @Transactional(rollbackOn = Exception.class)
    public List<Contato> createEntityList(Integer pessoaId, List<ContatoCleanDTO> contatos) {
        List<Contato> entityList = new ArrayList<>();
        for (ContatoCleanDTO contato : contatos) {
            Contato entity = repository.save(mapper.toEntity(contato));
            Pessoa pessoa = new Pessoa();
            pessoa.setId(pessoaId);
            entity.setPessoa(pessoa);
            entityList.add(entity);
        }
        return entityList;
    }

    @Transactional(rollbackOn = Exception.class)
    public List<Contato> updateEntityList(List<ContatoCleanDTO> contatos) {
        List<Contato> entityList = new ArrayList<>();
        for (ContatoCleanDTO contato : contatos) {
            var contatoClean = contato;
            if (contato.getId() != null) {
                contatoClean = this.update(contato.getId(), contato);
                var entity = mapper.toEntity(contatoClean);
                entityList.add(entity);
            } else {
                Contato novoContato = mapper.toEntity(contato);
                Contato contatoSalvo = repository.save(novoContato);
                entityList.add(contatoSalvo);
            }
        }
        return entityList;
    }

    public List<Contato> findEntityList() {
        List<Contato> entity = repository.findAll();
        return entity;
    }

    public void delete(Integer id) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado com o ID: " + id));

        repository.delete(contato);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteEntityList(List<Contato> contatos) {
        contatos.forEach(contato -> {
            repository.delete(contato);
        });
    }
}
