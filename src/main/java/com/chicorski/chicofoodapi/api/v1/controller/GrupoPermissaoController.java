package com.chicorski.chicofoodapi.api.v1.controller;

import com.chicorski.chicofoodapi.api.v1.ChicoLinks;
import com.chicorski.chicofoodapi.api.v1.assembler.PermissaoModelAssembler;
import com.chicorski.chicofoodapi.api.v1.model.PermissaoModel;
import com.chicorski.chicofoodapi.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.chicorski.chicofoodapi.core.security.ChicoFoodSecurity;
import com.chicorski.chicofoodapi.domain.model.Grupo;
import com.chicorski.chicofoodapi.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @Autowired
    private ChicoFoodSecurity chicoFoodSecurity;

    @Autowired
    private ChicoLinks chicoLinks;

    @GetMapping
    public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

        CollectionModel<PermissaoModel> permissoesModel
                = permissaoModelAssembler.toCollectionModel(grupo.getPermissoes())
                .removeLinks();

        permissoesModel.add(chicoLinks.linkToGrupoPermissoes(grupoId));

        if (chicoFoodSecurity.podeEditarUsuariosGruposPermissoes()) {
            permissoesModel.add(chicoLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

            permissoesModel.getContent().forEach(permissaoModel -> {
                permissaoModel.add(chicoLinks.linkToGrupoPermissaoDesassociacao(
                        grupoId, permissaoModel.getId(), "desassociar"));
            });
        }

        return permissoesModel;
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupo.desassociarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupo.associarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }

}