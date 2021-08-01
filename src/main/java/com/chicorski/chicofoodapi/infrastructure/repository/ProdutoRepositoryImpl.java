package com.chicorski.chicofoodapi.infrastructure.repository;

import com.chicorski.chicofoodapi.domain.model.FotoProduto;
import com.chicorski.chicofoodapi.domain.repository.ProdutoRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public FotoProduto save(FotoProduto fotoProduto) {
        return manager.merge(fotoProduto);
    }

    @Override
    public void delete(FotoProduto fotoProduto) {
        manager.remove(fotoProduto);
    }
}
