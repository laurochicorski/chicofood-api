package com.chicorski.chicofoodapi.domain.service;

import com.chicorski.chicofoodapi.domain.exception.PermissaoNaoEncontradaException;
import com.chicorski.chicofoodapi.domain.model.Permissao;
import com.chicorski.chicofoodapi.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }
}