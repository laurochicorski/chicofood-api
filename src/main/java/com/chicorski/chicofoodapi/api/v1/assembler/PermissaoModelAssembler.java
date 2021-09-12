package com.chicorski.chicofoodapi.api.v1.assembler;

import com.chicorski.chicofoodapi.api.v1.ChicoLinks;
import com.chicorski.chicofoodapi.api.v1.model.PermissaoModel;
import com.chicorski.chicofoodapi.core.security.ChicoFoodSecurity;
import com.chicorski.chicofoodapi.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PermissaoModelAssembler implements RepresentationModelAssembler<Permissao, PermissaoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ChicoLinks chicoLinks;

    @Autowired
    private ChicoFoodSecurity chicoFoodSecurity;


    @Override
    public PermissaoModel toModel(Permissao permissao) {
        PermissaoModel permissaoModel = modelMapper.map(permissao, PermissaoModel.class);
        return permissaoModel;
    }

    @Override
    public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
        CollectionModel<PermissaoModel> collectionModel
                = RepresentationModelAssembler.super.toCollectionModel(entities);

        if (chicoFoodSecurity.podeConsultarUsuariosGruposPermissoes()) {
            collectionModel.add(chicoLinks.linkToPermissoes());
        }

        return collectionModel;
    }

}