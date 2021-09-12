package com.chicorski.chicofoodapi.domain.listener;

import com.chicorski.chicofoodapi.domain.event.PedidoConfirmadoEvent;
import com.chicorski.chicofoodapi.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoConfirmadoListener {

    @Autowired
    private EnvioEmailService envioEmail;

    @TransactionalEventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        var mensagem = EnvioEmailService.Mensagem.builder()
        .assunto(event.getPedido().getRestaurante().getNome() + " - Pedido confirmado.")
        .corpo("emails/pedido-confirmado.html")
        .variavel("pedido", event.getPedido())
        .destinatario(event.getPedido().getCliente().getEmail())
        .build();

        envioEmail.enviar(mensagem);
    }
}
