package com.chicorski.chicofoodapi.api.v1.assembler;

import com.chicorski.chicofoodapi.api.v1.ChicoLinks;
import com.chicorski.chicofoodapi.api.v1.controller.GrupoController;
import com.chicorski.chicofoodapi.api.v1.model.GrupoModel;
import com.chicorski.chicofoodapi.core.security.ChicoFoodSecurity;
import com.chicorski.chicofoodapi.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ChicoLinks chicoLinks;

    @Autowired
    private ChicoFoodSecurity chicoFoodSecurity;

    public GrupoModelAssembler() {
        super(GrupoController.class, GrupoModel.class);
    }

    @Override
    public GrupoModel toModel(Grupo grupo) {
        GrupoModel grupoModel = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoModel);

        if (chicoFoodSecurity.podeConsultarUsuariosGruposPermissoes()) {
            grupoModel.add(chicoLinks.linkToGrupos("grupos"));

            grupoModel.add(chicoLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
        }

        return grupoModel;
    }

    @Override
    public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {
        CollectionModel<GrupoModel> collectionModel = super.toCollectionModel(entities);

        if (chicoFoodSecurity.podeConsultarUsuariosGruposPermissoes()) {
            collectionModel.add(chicoLinks.linkToGrupos());
        }

        return collectionModel;
    }
}
