package com.chicorski.chicofoodapi.domain.service;

import com.chicorski.chicofoodapi.domain.model.Pedido;
import com.chicorski.chicofoodapi.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void confirmar(String codigo) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);

        pedido.confirmar();
        pedido.setDataConfirmacao(OffsetDateTime.now());

        pedidoRepository.save(pedido);


    }

    @Transactional
    public void cancelar(String codigo) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);
        pedido.cancelar();

        pedidoRepository.save(pedido);
    }

    @Transactional
    public void entregar(String codigo) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);

        pedido.entregar();
        pedido.setDataEntrega(OffsetDateTime.now());
    }
}
