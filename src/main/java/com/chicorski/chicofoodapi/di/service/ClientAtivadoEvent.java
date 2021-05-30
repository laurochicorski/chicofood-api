package com.chicorski.chicofoodapi.di.service;

import com.chicorski.chicofoodapi.di.modelo.Cliente;

public class ClientAtivadoEvent {

    private Cliente cliente;

    public ClientAtivadoEvent(Cliente cliente) {
        super();
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
