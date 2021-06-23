package com.chicorski.chicofoodapi.api.controller;

import com.chicorski.chicofoodapi.domain.exception.EstadoNaoEncontradaException;
import com.chicorski.chicofoodapi.domain.exception.NegocioException;
import com.chicorski.chicofoodapi.domain.model.Cidade;
import com.chicorski.chicofoodapi.domain.repository.CidadeRepository;
import com.chicorski.chicofoodapi.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @GetMapping
    private List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    private Cidade buscar(@PathVariable Long id) {
        return cadastroCidade.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Cidade adicionar(@RequestBody @Valid Cidade cidade) {
        try {
            return cadastroCidade.salvar(cidade);
        } catch (EstadoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @PutMapping("/{id}")
    private Cidade atualizar(@PathVariable Long id, @RequestBody @Valid Cidade cidade) {
        Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        try {
            return cadastroCidade.salvar(cidadeAtual);
        }catch (EstadoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void remover(@PathVariable Long id) {
        cadastroCidade.excluir(id);
    }
}
