package com.elotech.gestaopessoaapi.domain.dbo.pessoa;

import java.util.List;

import org.springdoc.core.annotations.RouterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaCleanDTO;
import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaFullDTO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService service;

    @GetMapping("/full/{id}")
    public PessoaFullDTO findFullById(@PathVariable Integer id) {
        return service.findFullById(id);
    }

    @RouterOperation(operation = @Operation(summary = "Retorna uma lista paginada de um predicate - customizada"))
    @GetMapping(value = "/pageable")
    public Page<PessoaCleanDTO> findPageable(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "cpf", required = false) String cpf,
            @RequestParam(name = "currentPage", required = false) Integer currentPage,
            @RequestParam(name = "itemsPerPage", required = false) Integer itemsPerPage,
            Pageable pageable) {
        return service.findPageable(nome, cpf, currentPage, itemsPerPage, pageable);
    }

    @PostMapping
    public PessoaFullDTO createFull(@RequestBody @Valid PessoaFullDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaFullDTO> updateFull(@PathVariable Integer id, @RequestBody @Valid PessoaFullDTO dto) {
        service.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Exclusão de Entidade")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}