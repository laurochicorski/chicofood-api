package com.chicorski.chicofoodapi.api.v1.controller;

import com.chicorski.chicofoodapi.api.v1.ChicoLinks;
import com.chicorski.chicofoodapi.core.security.ChicoFoodSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointcontroller {

    @Autowired
    private ChicoLinks chicoLinks;

    @Autowired
    private ChicoFoodSecurity chicoFoodSecurity;

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        if (chicoFoodSecurity.podeConsultarCozinhas()) {
            rootEntryPointModel.add(chicoLinks.linkToCozinhas("cozinhas"));
        }

        if (chicoFoodSecurity.podePesquisarPedidos()) {
            rootEntryPointModel.add(chicoLinks.linkToPedidos("pedidos"));
        }

        if (chicoFoodSecurity.podeConsultarRestaurantes()) {
            rootEntryPointModel.add(chicoLinks.linkToRestaurantes("restaurantes"));
        }

        if (chicoFoodSecurity.podeConsultarUsuariosGruposPermissoes()) {
            rootEntryPointModel.add(chicoLinks.linkToGrupos("grupos"));
        }

        if (chicoFoodSecurity.podeConsultarFormasPagamento()) {
            rootEntryPointModel.add(chicoLinks.linkToUsuarios("usuarios"));
        }


        rootEntryPointModel.add(chicoLinks.linkToPermissoes("permissoes"));
        rootEntryPointModel.add(chicoLinks.linkToFormasPagamento("formas-pagamento"));

        if (chicoFoodSecurity.podeConsultarEstados()) {
            rootEntryPointModel.add(chicoLinks.linkToEstados("estados"));
        }

        if (chicoFoodSecurity.podeConsultarCidades()) {
            rootEntryPointModel.add(chicoLinks.linkToCidades("cidades"));
        }

        if (chicoFoodSecurity.podeConsultarEstatisticas()) {
            rootEntryPointModel.add(chicoLinks.linkToEstatisticas("estatisticas"));
        }

        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }
}
