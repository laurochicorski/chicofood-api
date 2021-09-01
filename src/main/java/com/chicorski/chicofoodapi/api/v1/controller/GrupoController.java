package com.chicorski.chicofoodapi.api.v1.controller;

import com.chicorski.chicofoodapi.api.v1.assembler.GrupoInputDisassembler;
import com.chicorski.chicofoodapi.api.v1.assembler.GrupoModelAssembler;
import com.chicorski.chicofoodapi.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.chicorski.chicofoodapi.api.v1.model.GrupoModel;
import com.chicorski.chicofoodapi.api.v1.model.input.GrupoInput;
import com.chicorski.chicofoodapi.domain.model.Grupo;
import com.chicorski.chicofoodapi.domain.repository.GrupoRepository;
import com.chicorski.chicofoodapi.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @Override
    @GetMapping
    public CollectionModel<GrupoModel> listar() {
        List<Grupo> todosGrupos = grupoRepository.findAll();

        return grupoModelAssembler.toCollectionModel(todosGrupos);
    }

    @GetMapping("/{id}")
    public GrupoModel buscar(@PathVariable Long id) {
        Grupo grupo = cadastroGrupo.buscarOuFalhar(id);

        return grupoModelAssembler.toModel(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);

        grupo = cadastroGrupo.salvar(grupo);

        return grupoModelAssembler.toModel(grupo);
    }

    @PutMapping("/{id}")
    public GrupoModel atualizar(@PathVariable Long id,
                                         @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(id);

        grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);

        grupoAtual = cadastroGrupo.salvar(grupoAtual);

        return grupoModelAssembler.toModel(grupoAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroGrupo.excluir(id);
    }
}
