package com.chicorski.chicofoodapi.api.v1.assembler;

import com.chicorski.chicofoodapi.api.v1.ChicoLinks;
import com.chicorski.chicofoodapi.api.v1.controller.PedidoController;
import com.chicorski.chicofoodapi.api.v1.model.PedidoResumoModel;
import com.chicorski.chicofoodapi.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ChicoLinks chicoLinks;

    public PedidoResumoModelAssembler() {
        super(PedidoController.class, PedidoResumoModel.class);
    }

    @Override
    public PedidoResumoModel toModel(Pedido pedido) {
        PedidoResumoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);

        pedidoModel.add(chicoLinks.linkToPedidos("pedidos"));

        pedidoModel.getRestaurante().add(
                chicoLinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoModel.getCliente().add(chicoLinks.linkToUsuario(pedido.getCliente().getId()));

        return pedidoModel;
    }

}