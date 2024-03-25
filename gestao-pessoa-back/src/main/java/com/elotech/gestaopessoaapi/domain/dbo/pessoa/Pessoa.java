package com.elotech.gestaopessoaapi.domain.dbo.pessoa;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.br.CPF;

import com.elotech.gestaopessoaapi.domain.dbo.contato.Contato;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PESSOA")
    private Integer id;

    @Column(name = "NM_PESSOA")
    private String nome;

    @CPF
    @Column(name = "CD_CPF")
    private String cpf;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate dataNascimento;

    // bi-directional many-to-one association to Contato
    @OneToMany(mappedBy = "pessoa")
    private List<Contato> contatos;

}