package com.chicorski.chicofoodapi.di.service;

import com.chicorski.chicofoodapi.di.modelo.Cliente;
import com.chicorski.chicofoodapi.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivicaoClienteService {

    @Autowired
    private Notificador notificador;

//    @Autowired
    public AtivicaoClienteService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }

//    @Autowired
//    public void setNotificador(Notificador notificador) {
//        this.notificador = notificador;
//    }
}
