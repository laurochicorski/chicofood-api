package com.chicorski.chicofoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

    @Embedded
    private Endereco endereco;

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento"
            , joinColumns = @JoinColumn(name="restaurante_id")
            , inverseJoinColumns = @JoinColumn(name="forma_pagamento_id"))
    private Set<FormaPagamento> formasPagamento = new LinkedHashSet<>();

    @OneToMany(mappedBy = "restaurante")
    private Set<Produto> produtos = new LinkedHashSet<>();

    private Boolean ativo = Boolean.TRUE;

    public void ativar() {
        setAtivo(true);
    }

    public void inativar() {
        setAtivo(false);
    }

    public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
        return formasPagamento.add(formaPagamento);
    }

    public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
        return formasPagamento.remove(formaPagamento);
    }
}
