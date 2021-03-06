package com.chicorski.chicofoodapi.api.v1.controller;

import com.chicorski.chicofoodapi.api.ResourceUriHelper;
import com.chicorski.chicofoodapi.api.v1.assembler.CidadeInputDisassembler;
import com.chicorski.chicofoodapi.api.v1.assembler.CidadeModelAssembler;
import com.chicorski.chicofoodapi.api.v1.model.CidadeModel;
import com.chicorski.chicofoodapi.api.v1.model.input.CidadeInput;
import com.chicorski.chicofoodapi.api.v1.openapi.controller.CidadeControllerOpenApi;
import com.chicorski.chicofoodapi.core.security.CheckSecurity;
import com.chicorski.chicofoodapi.domain.exception.EstadoNaoEncontradaException;
import com.chicorski.chicofoodapi.domain.exception.NegocioException;
import com.chicorski.chicofoodapi.domain.model.Cidade;
import com.chicorski.chicofoodapi.domain.repository.CidadeRepository;
import com.chicorski.chicofoodapi.domain.service.CadastroCidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path ="/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    private static final Logger logger = LoggerFactory.getLogger(CozinhaController.class);

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @CheckSecurity.Cidades.PodeConsultar
    @Deprecated
    @GetMapping
    public CollectionModel<CidadeModel> listar() {
        logger.info("Consultando cidades....");
        List<Cidade> todasCidades = cidadeRepository.findAll();

        return cidadeModelAssembler.toCollectionModel(todasCidades);
    }

    @CheckSecurity.Cidades.PodeConsultar
    @GetMapping("/{id}")
    public CidadeModel buscar(@PathVariable Long id) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(id);

        return cidadeModelAssembler.toModel(cidade);
    }

    @CheckSecurity.Cidades.PodeEditar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            cidade = cadastroCidade.salvar(cidade);

            CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);

            ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());

            return cidadeModel;
        } catch (EstadoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @CheckSecurity.Cidades.PodeEditar
    @PutMapping("/{id}")
    public CidadeModel atualizar(@PathVariable Long id, @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);

            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

            cidadeAtual = cadastroCidade.salvar(cidadeAtual);

            return cidadeModelAssembler.toModel(cidadeAtual);
        }catch (EstadoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Cidades.PodeEditar
    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        cadastroCidade.excluir(id);
    }
}
