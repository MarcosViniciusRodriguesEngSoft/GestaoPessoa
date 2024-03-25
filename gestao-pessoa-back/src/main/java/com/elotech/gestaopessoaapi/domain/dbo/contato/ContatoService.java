package com.elotech.gestaopessoaapi.domain.dbo.contato;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.elotech.gestaopessoaapi.domain.dbo.contato.dto.ContatoCleanDTO;

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

    public ContatoCleanDTO findById(@PathVariable Integer id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado com o ID: " + id));

        return mapper.toContatoCleanDTO(entity);
    }

    public List<ContatoCleanDTO> findCleanList() {
        List<Contato> entity = repository.findAll();
        return entity.stream()
                .map(mapper::toContatoCleanDTO)
                .collect(Collectors.toList());
    }

    public List<Contato> findEntityList() {
        List<Contato> entity = repository.findAll();
        return entity;
    }

    public ContatoCleanDTO createClean(@RequestBody ContatoCleanDTO dto) {
        Contato contato = new Contato();
        contato.setNome(dto.getNome());
        contato.setEmail(dto.getEmail());
        contato.setTelefone(dto.getTelefone());

        repository.save(contato);

        return mapper.toContatoDTO(contato);
    }

    public ContatoCleanDTO update(@PathVariable Integer id, @RequestBody ContatoCleanDTO dto) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado com o ID: " + id));

        contato.setNome(dto.getNome());
        contato.setTelefone(dto.getTelefone());
        contato.setEmail(dto.getEmail());

        var entity = repository.save(contato);

        return mapper.toContatoCleanDTO(entity);
    }

    public void delete(@PathVariable Integer id) {
        Contato contato = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado com o ID: " + id));

        repository.delete(contato);
    }

    @Transactional(rollbackOn = Exception.class)
    public List<Contato> createEntityList(List<ContatoCleanDTO> contatos) {
        List<Contato> entityList = new ArrayList<>();
        for (ContatoCleanDTO contato : contatos) {
            entityList.add(repository.save(mapper.toEntity(contato)));
        }
        return entityList;
    }

    @Transactional(rollbackOn = Exception.class)
    public List<Contato> updateEntityList(List<ContatoCleanDTO> contatos) {
        List<Contato> entityList = new ArrayList<>();
        for (ContatoCleanDTO contato : contatos) {
            var contatoClean = this.update(contato.getId(), contato);

            var entity = mapper.toEntity(contatoClean);
            entityList.add(entity);
        }
        return entityList;
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteEntityList(List<Contato> contatos) {
        contatos.forEach(contato -> {
            repository.delete(contato);
        });
    }

}
