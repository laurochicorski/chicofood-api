package com.chicorski.chicofoodapi.domain.service;

import com.chicorski.chicofoodapi.domain.exception.NegocioException;
import com.chicorski.chicofoodapi.domain.model.Pedido;
import com.chicorski.chicofoodapi.domain.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);

        if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
            throw new NegocioException(String.format("Status do pedido %s não pode ser alterado de %s para %s", pedidoId, pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
        }

        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }
}
