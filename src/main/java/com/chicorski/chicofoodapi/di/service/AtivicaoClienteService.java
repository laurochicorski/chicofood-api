package com.chicorski.chicofoodapi.di.service;

import com.chicorski.chicofoodapi.di.modelo.Cliente;
import com.chicorski.chicofoodapi.di.notificacao.Notificador;
import org.springframework.stereotype.Component;

@Component
public class AtivicaoClienteService {

    private Notificador notificador;

    public AtivicaoClienteService(Notificador notificador) {
        this.notificador = notificador;

        System.out.println("AtivicaoCLienteService: " + notificador);
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }
}
