package com.chicorski.chicofoodapi.domain.service;

import com.chicorski.chicofoodapi.domain.exception.EntidadeEmUsoException;
import com.chicorski.chicofoodapi.domain.exception.GrupoNaoEncontradaException;
import com.chicorski.chicofoodapi.domain.model.Grupo;
import com.chicorski.chicofoodapi.domain.model.Permissao;
import com.chicorski.chicofoodapi.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroGrupoService {

    private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroPermissaoService cadastroPermissao;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GrupoNaoEncontradaException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_GRUPO_EM_USO, id));
        }
    }

    public Grupo buscarOuFalhar(Long id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new GrupoNaoEncontradaException(id));
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);

        grupo.removerPermissao(permissao);
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);

        grupo.adicionarPermissao(permissao);
    }

}
