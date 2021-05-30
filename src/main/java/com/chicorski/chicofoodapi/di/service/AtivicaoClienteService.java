package com.chicorski.chicofoodapi.di.service;

import com.chicorski.chicofoodapi.di.modelo.Cliente;
import com.chicorski.chicofoodapi.di.notificacao.NivelUrgencia;
import com.chicorski.chicofoodapi.di.notificacao.Notificador;
import com.chicorski.chicofoodapi.di.notificacao.TipoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivicaoClienteService {


    @Autowired
    @TipoNotificador(NivelUrgencia.SEM_URGENCIA)
    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }

}
