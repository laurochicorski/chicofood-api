package com.chicorski.chicofoodapi.api.v1.assembler;

import com.chicorski.chicofoodapi.api.v1.ChicoLinks;
import com.chicorski.chicofoodapi.api.v1.controller.CidadeController;
import com.chicorski.chicofoodapi.api.v1.model.CidadeModel;
import com.chicorski.chicofoodapi.core.security.ChicoFoodSecurity;
import com.chicorski.chicofoodapi.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ChicoLinks chicoLinks;

    @Autowired
    private ChicoFoodSecurity chicoFoodSecurity;

    public CidadeModelAssembler() {
        super(CidadeController.class, CidadeModel.class);
    }

    @Override
    public CidadeModel toModel(Cidade cidade) {
        CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);

        modelMapper.map(cidade, cidadeModel);

        if (chicoFoodSecurity.podeConsultarCidades()) {
            cidadeModel.add(chicoLinks.linkToCidades("cidades"));
        }

        if (chicoFoodSecurity.podeConsultarEstados()) {
            cidadeModel.getEstado().add(chicoLinks.linkToEstado(cidadeModel.getEstado().getId()));
        }

        return cidadeModel;
    }

    @Override
    public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
        CollectionModel<CidadeModel> collectionModel = super.toCollectionModel(entities);

        if (chicoFoodSecurity.podeConsultarCidades()) {
            collectionModel.add(chicoLinks.linkToCidades());
        }

        return collectionModel;
    }
}
