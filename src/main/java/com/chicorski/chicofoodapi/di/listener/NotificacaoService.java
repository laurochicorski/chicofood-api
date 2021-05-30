package com.chicorski.chicofoodapi.di.listener;

import com.chicorski.chicofoodapi.di.notificacao.NivelUrgencia;
import com.chicorski.chicofoodapi.di.notificacao.Notificador;
import com.chicorski.chicofoodapi.di.notificacao.NotificadorEmail;
import com.chicorski.chicofoodapi.di.notificacao.TipoNotificador;
import com.chicorski.chicofoodapi.di.service.ClientAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    @Autowired
    @TipoNotificador(NivelUrgencia.SEM_URGENCIA)
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClientAtivadoEvent event) {
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo");
    }
}
