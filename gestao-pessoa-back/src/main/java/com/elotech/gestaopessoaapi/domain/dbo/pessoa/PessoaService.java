package com.elotech.gestaopessoaapi.domain.dbo.pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elotech.gestaopessoaapi.domain.dbo.contato.Contato;
import com.elotech.gestaopessoaapi.domain.dbo.contato.ContatoService;
import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaCleanDTO;
import com.elotech.gestaopessoaapi.domain.dbo.pessoa.dto.PessoaFullDTO;
import com.elotech.gestaopessoaapi.domain.util.StringUtil;
import com.elotech.gestaopessoaapi.exception.BadRequestException;

@Service
public class PessoaService {

    private final PessoaRepository repository;
    private final PessoaMapper mapper;
    private final ContatoService contatoService;

    public PessoaService(
            PessoaRepository repository,
            ContatoService contatoService,
            PessoaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.contatoService = contatoService;
    }

    public Pessoa findById(Integer id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o ID: " + id));
        return pessoa;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createEntity(Pessoa entity) {
        repository.save(entity);
    }

    public PessoaFullDTO findFullById(Integer id) {
        Pessoa pessoa = this.findById(id);

        return mapper.toPessoaFullDTO(pessoa);
    }

    @Transactional(readOnly = true)
    public Page<PessoaCleanDTO> findPageable(String nome, String cpf, Integer currentPage, Integer itemsPerPage,
            Pageable pageable) {
        List<Pessoa> entityList = repository.findAll();

        List<Pessoa> filteredList = entityList.stream()
                .filter(el -> (nome == null || el.getNome().toLowerCase().contains(nome.toLowerCase())))
                .collect(Collectors.toList());

        List<PessoaCleanDTO> filteredListClean = mapper.toCleanDTO(filteredList);

        int startItem = (currentPage - 1) * itemsPerPage;

        List<PessoaCleanDTO> pageList = new ArrayList<>();
        if (filteredListClean.size() < startItem) {
            pageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + itemsPerPage, filteredListClean.size());
            pageList = filteredListClean.subList(startItem, toIndex);
        }

        Page<PessoaCleanDTO> pessoaPageable = new PageImpl<>(pageList, pageable, filteredListClean.size());

        return pessoaPageable;
    }

    public PessoaFullDTO create(PessoaFullDTO dto) {
        Pessoa pessoa = new Pessoa();

        pessoa.setNome(dto.getNome());

        if (!StringUtil.validarCPF(dto.getCpf())) {
            throw new BadRequestException("O CPF informado não está em um formato válido.");
        }
        pessoa.setCpf(dto.getCpf());

        if (dto.getDataNascimento().isAfter(LocalDate.now())) {
            throw new BadRequestException("A data informada não pode se futura a data de hoje.");
        }
        pessoa.setDataNascimento(dto.getDataNascimento());

        Pessoa entity = repository.save(pessoa);

        if (dto.getContatos().isEmpty()) {
            throw new BadRequestException("O cadastro deve conter pelo menos um contato.");
        }
        contatoService.createEntityList(entity.getId(), dto.getContatos());

        repository.save(entity);

        return mapper.toPessoaFullDTO(entity);
    }

    public PessoaFullDTO update(Integer id, PessoaFullDTO dto) {
        Pessoa pessoa = this.findById(id);

        pessoa.setNome(dto.getNome());

        if (!StringUtil.validarCPF(dto.getCpf())) {
            throw new BadRequestException("O CPF informado não está em um formato válido.");
        }
        pessoa.setCpf(dto.getCpf());

        if (dto.getDataNascimento().isAfter(LocalDate.now())) {
            throw new BadRequestException("A data informada não pode ser futura à data de hoje.");
        }
        pessoa.setDataNascimento(dto.getDataNascimento());

        if (dto.getContatos() == null || dto.getContatos().isEmpty()) {
            throw new BadRequestException("O cadastro deve conter pelo menos um contato.");
        } else {
            if (dto.getContatos().size() > 0 || pessoa.getContatos().isEmpty()) {
                List<Contato> contatos = contatoService.updateEntityList(dto.getContatos());
                Pessoa pessoaContato = new Pessoa();
                pessoaContato.setId(pessoa.getId());
                contatos.forEach(c -> c.setPessoa(pessoaContato));
                pessoa.setContatos(contatos);
            }
        }

        pessoa = repository.save(pessoa);
        return mapper.toPessoaFullDTO(pessoa);
    }

    public void delete(Integer id) {
        Pessoa pessoa = this.findById(id);

        contatoService.deleteEntityList(pessoa.getContatos());
        repository.delete(pessoa);
    }
}
