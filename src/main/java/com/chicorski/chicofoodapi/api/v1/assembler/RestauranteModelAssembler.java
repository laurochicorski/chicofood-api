package com.chicorski.chicofoodapi.api.v1.assembler;

import com.chicorski.chicofoodapi.api.v1.ChicoLinks;
import com.chicorski.chicofoodapi.api.v1.controller.RestauranteController;
import com.chicorski.chicofoodapi.api.v1.model.RestauranteModel;
import com.chicorski.chicofoodapi.core.security.ChicoFoodSecurity;
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

    @Autowired
    private ChicoFoodSecurity chicoFoodSecurity;

    public RestauranteModelAssembler() {
        super(RestauranteController.class, RestauranteModel.class);
    }

    @Override
    public RestauranteModel toModel(Restaurante restaurante) {
        RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteModel);

        if (chicoFoodSecurity.podeConsultarRestaurantes()) {
            restauranteModel.add(chicoLinks.linkToRestaurantes("restaurantes"));
        }

        if (chicoFoodSecurity.podeGerenciarCadastroRestaurantes()) {
            if (restaurante.ativacaoPermitida()) {
                restauranteModel.add(
                        chicoLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
            }

            if (restaurante.inativacaoPermitida()) {
                restauranteModel.add(
                        chicoLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
            }
        }

        if (chicoFoodSecurity.podeGerenciarFuncionamentoRestaurantes(restaurante.getId())) {
            if (restaurante.aberturaPermitida()) {
                restauranteModel.add(
                        chicoLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
            }

            if (restaurante.fechamentoPermitido()) {
                restauranteModel.add(
                        chicoLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
            }
        }

        if (chicoFoodSecurity.podeConsultarRestaurantes()) {
            restauranteModel.add(chicoLinks.linkToProdutos(restaurante.getId(), "produtos"));
        }

        if (chicoFoodSecurity.podeConsultarCozinhas()) {
            restauranteModel.getCozinha().add(
                    chicoLinks.linkToCozinha(restaurante.getCozinha().getId()));
        }

        if (restauranteModel.getEndereco() != null
                && restauranteModel.getEndereco().getCidade() != null) {
            restauranteModel.getEndereco().getCidade().add(
                    chicoLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
        }

        if (chicoFoodSecurity.podeConsultarCidades()) {
            restauranteModel.add(chicoLinks.linkToRestauranteFormasPagamento(restaurante.getId(),
                    "formas-pagamento"));
        }

        if (chicoFoodSecurity.podeConsultarRestaurantes()) {
            restauranteModel.add(chicoLinks.linkToRestauranteResponsaveis(restaurante.getId(),
                    "responsaveis"));
        }

        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(chicoLinks.linkToRestaurantes());
    }
}
