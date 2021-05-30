package com.chicorski.chicofoodapi.di.notificacao;

import com.chicorski.chicofoodapi.di.modelo.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("normal")
@Component
public class NotificadorEmail implements Notificador {

    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
