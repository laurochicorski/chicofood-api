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

//        var mensagem = EnvioEmailService.Mensagem.builder()
//                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado.")
//                .corpo("pedido-confirmado.html")
//                .variavel("pedido", pedido)
//                .destinatario(pedido.getCliente().getEmail())
//                .build();
//
//        envioEmail.enviar(mensagem);
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
