package com.chicorski.chicofoodapi.api.model.mixin;

import com.chicorski.chicofoodapi.domain.model.Cozinha;
import com.chicorski.chicofoodapi.domain.model.Endereco;
import com.chicorski.chicofoodapi.domain.model.FormaPagamento;
import com.chicorski.chicofoodapi.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();
}
