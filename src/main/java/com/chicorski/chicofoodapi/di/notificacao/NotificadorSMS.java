package com.chicorski.chicofoodapi.di.notificacao;

import com.chicorski.chicofoodapi.di.modelo.Cliente;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class NotificadorSMS implements Notificador {

    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s por SMS através do telefone %s: %s\n",
                cliente.getNome(), cliente.getTelefone(), mensagem);
    }
}
