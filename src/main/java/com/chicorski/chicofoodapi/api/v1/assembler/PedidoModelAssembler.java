package com.chicorski.chicofoodapi.api.v1.assembler;

import com.chicorski.chicofoodapi.api.v1.ChicoLinks;
import com.chicorski.chicofoodapi.api.v1.controller.*;
import com.chicorski.chicofoodapi.api.v1.model.PedidoModel;
import com.chicorski.chicofoodapi.core.security.ChicoFoodSecurity;
import com.chicorski.chicofoodapi.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel>  {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ChicoLinks chicoLinks;

    @Autowired
    private ChicoFoodSecurity chicoFoodSecurity;

    public PedidoModelAssembler() {
        super(PedidoController.class, PedidoModel.class);
    }

    @Override
    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);

        pedidoModel.add(chicoLinks.linkToPedidos("pedidos"));

        if (chicoFoodSecurity.podeGerenciarPedidos(pedido.getCodigo())) {
            if (pedido.podeSerConfirmado()) {
                pedidoModel.add(chicoLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
            }

            if (pedido.podeSerEntregue()) {
                pedidoModel.add(chicoLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
            }

            if (pedido.podeSerCancelado()) {
                pedidoModel.add(chicoLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
            }
        }

        if (chicoFoodSecurity.podeConsultarRestaurantes()) {
            pedidoModel.getRestaurante().add(
                    chicoLinks.linkToRestaurante(pedido.getRestaurante().getId()));
        }

        if (chicoFoodSecurity.podeConsultarUsuariosGruposPermissoes()) {
            pedidoModel.getCliente().add(
                    chicoLinks.linkToUsuario(pedido.getCliente().getId()));
        }

        if (chicoFoodSecurity.podeConsultarFormasPagamento()) {
            pedidoModel.getFormaPagamento().add(
                    chicoLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));
        }

        if (chicoFoodSecurity.podeConsultarCidades()) {
            pedidoModel.getEnderecoEntrega().getCidade().add(
                    chicoLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));
        }

        // Quem pode consultar restaurantes, também pode consultar os produtos dos restaurantes
        if (chicoFoodSecurity.podeConsultarRestaurantes()) {
            pedidoModel.getItens().forEach(item -> {
                item.add(chicoLinks.linkToProduto(
                        pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
            });
        }

        return pedidoModel;
    }

}