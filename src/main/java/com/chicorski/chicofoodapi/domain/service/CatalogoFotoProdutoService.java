package com.chicorski.chicofoodapi.domain.service;

import com.chicorski.chicofoodapi.domain.model.FotoProduto;
import com.chicorski.chicofoodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public FotoProduto salvar(FotoProduto fotoProduto) {
        Long restauranteId = fotoProduto.getRestauranteId();
        Long produtoId = fotoProduto.getProduto().getId();

        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
            produtoRepository.delete(fotoExistente.get());
        }

        return produtoRepository.save(fotoProduto);
    }
}
