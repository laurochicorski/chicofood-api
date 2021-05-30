package com.chicorski.chicofoodapi.di.notificacao;

import com.chicorski.chicofoodapi.di.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@TipoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

    @Autowired
    private NotificadorProperties properties;

    public NotificadorEmail() {
        System.out.println("Notificador e-mail real.");
    }

    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Host: " + properties.getHostServidor());
        System.out.println("Port: " + properties.getPortaServidor());
        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
