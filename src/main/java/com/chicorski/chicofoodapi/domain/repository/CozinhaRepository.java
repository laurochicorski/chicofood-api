package com.chicorski.chicofoodapi.domain.repository;

import com.chicorski.chicofoodapi.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> listar();
    Cozinha buscar(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remover(Cozinha cozinha);
}
