package com.chicorski.chicofoodapi.di.notificacao;

import com.chicorski.chicofoodapi.di.modelo.Cliente;

public interface Notificador {

    void notificar(Cliente cliente, String mensagem);

}
