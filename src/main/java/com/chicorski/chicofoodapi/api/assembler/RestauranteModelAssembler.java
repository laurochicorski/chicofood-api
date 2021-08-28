package com.chicorski.chicofoodapi.api.assembler;

import com.chicorski.chicofoodapi.api.ChicoLinks;
import com.chicorski.chicofoodapi.api.controller.RestauranteController;
import com.chicorski.chicofoodapi.api.model.RestauranteModel;
import com.chicorski.chicofoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelAssembler
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ChicoLinks chicoLinks;

    public RestauranteModelAssembler() {
        super(RestauranteController.class, RestauranteModel.class);
    }

    @Override
    public RestauranteModel toModel(Restaurante restaurante) {
        RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteModel);

        restauranteModel.add(chicoLinks.linkToRestaurantes("restaurantes"));

        if (restaurante.ativacaoPermitida()) {
            restauranteModel.add(
                    chicoLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
        }

        if (restaurante.inativacaoPermitida()) {
            restauranteModel.add(
                    chicoLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
        }

        if (restaurante.aberturaPermitida()) {
            restauranteModel.add(
                    chicoLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
        }

        if (restaurante.fechamentoPermitido()) {
            restauranteModel.add(
                    chicoLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
        }

        restauranteModel.add(chicoLinks.linkToProdutos(restaurante.getId(), "produtos"));

        restauranteModel.getCozinha().add(
                chicoLinks.linkToCozinha(restaurante.getCozinha().getId()));

        if (restauranteModel.getEndereco() != null
                && restauranteModel.getEndereco().getCidade() != null) {
            restauranteModel.getEndereco().getCidade().add(
                    chicoLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
        }

        restauranteModel.add(chicoLinks.linkToRestauranteFormasPagamento(restaurante.getId(),
                "formas-pagamento"));

        restauranteModel.add(chicoLinks.linkToRestauranteResponsaveis(restaurante.getId(),
                "responsaveis"));

        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(chicoLinks.linkToRestaurantes());
    }
}
