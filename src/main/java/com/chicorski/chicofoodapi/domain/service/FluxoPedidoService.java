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
    private EmissaoPedidoService emissaoPedido;

    @Transactional
    public void confirmar(String codigo) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);

        pedido.confirmar();
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }

    @Transactional
    public void cancelar(String codigo) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);

        pedido.cancelar();
        pedido.setDataCancelamento(OffsetDateTime.now());
    }

    @Transactional
    public void entregar(String codigo) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);

        pedido.entregar();
        pedido.setDataEntrega(OffsetDateTime.now());
    }
}
