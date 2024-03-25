package com.elotech.gestaopessoaapi.domain.dbo.contato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elotech.gestaopessoaapi.domain.dbo.contato.dto.ContatoCleanDTO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {
    @Autowired
    private ContatoService service;

    @GetMapping("/{id}")
    public ContatoCleanDTO getContatoById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping("/clean/list")
    public List<ContatoCleanDTO> findCleanList() {
        return service.findCleanList();
    }

    @PostMapping
    public ContatoCleanDTO createContato(@RequestBody @Valid ContatoCleanDTO dto) {
        return service.createClean(dto);
    }

    @PutMapping("/{id}")
    public ContatoCleanDTO updateContato(@PathVariable Integer id, @RequestBody ContatoCleanDTO dto) {
        return service.update(id, dto);
    }

    @Operation(summary = "Exclusão de Entidade")
    @DeleteMapping("/{id}")
    public void deleteContato(@PathVariable Integer id) {
        service.delete(id);
    }
}