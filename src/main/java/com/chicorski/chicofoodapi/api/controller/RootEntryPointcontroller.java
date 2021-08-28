package com.chicorski.chicofoodapi.api.controller;

import com.chicorski.chicofoodapi.api.ChicoLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointcontroller {

    @Autowired
    private ChicoLinks chicoLinks;

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(chicoLinks.linkToCozinhas("cozinhas"));
        rootEntryPointModel.add(chicoLinks.linkToPedidos("pedidos"));
        rootEntryPointModel.add(chicoLinks.linkToRestaurantes("restaurantes"));
        rootEntryPointModel.add(chicoLinks.linkToGrupos("grupos"));
        rootEntryPointModel.add(chicoLinks.linkToUsuarios("usuarios"));
        rootEntryPointModel.add(chicoLinks.linkToPermissoes("permissoes"));
        rootEntryPointModel.add(chicoLinks.linkToFormasPagamento("formas-pagamento"));
        rootEntryPointModel.add(chicoLinks.linkToEstados("estados"));
        rootEntryPointModel.add(chicoLinks.linkToCidades("cidades"));

        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }
}
