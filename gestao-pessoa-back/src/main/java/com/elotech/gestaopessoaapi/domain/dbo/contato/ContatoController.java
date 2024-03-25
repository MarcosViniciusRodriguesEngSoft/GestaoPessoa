package com.elotech.gestaopessoaapi.domain.dbo.contato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {
    
    @Autowired
    private ContatoService service;

    @Operation(summary = "Exclusão de Entidade")
    @DeleteMapping("/{id}")
    public void deleteContato(@PathVariable Integer id) {
        service.delete(id);
    }
}