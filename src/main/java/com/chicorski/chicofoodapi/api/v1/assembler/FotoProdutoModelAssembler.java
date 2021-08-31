package com.chicorski.chicofoodapi.api.v1.assembler;

import com.chicorski.chicofoodapi.api.v1.ChicoLinks;
import com.chicorski.chicofoodapi.api.v1.controller.RestauranteProdutoFotoController;
import com.chicorski.chicofoodapi.api.v1.model.FotoProdutoModel;
import com.chicorski.chicofoodapi.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ChicoLinks chicoLinks;

    public FotoProdutoModelAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
    }

    @Override
    public FotoProdutoModel toModel(FotoProduto foto) {
        FotoProdutoModel fotoProdutoModel = modelMapper.map(foto, FotoProdutoModel.class);

        fotoProdutoModel.add(chicoLinks.linkToFotoProduto(
                foto.getRestauranteId(), foto.getProduto().getId()));

        fotoProdutoModel.add(chicoLinks.linkToProduto(
                foto.getRestauranteId(), foto.getProduto().getId(), "produto"));

        return fotoProdutoModel;
    }
}
