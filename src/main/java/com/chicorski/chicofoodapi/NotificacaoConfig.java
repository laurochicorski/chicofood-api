package com.chicorski.chicofoodapi;

import com.chicorski.chicofoodapi.di.notificacao.NotificadorEmail;
import com.chicorski.chicofoodapi.di.service.AtivicaoClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoConfig {
    @Bean
    public NotificadorEmail notificadorEmail() {
        NotificadorEmail notificador = new NotificadorEmail("smtp.algamail.com.br");
        notificador.setCaixaAlta(true);
        return notificador;
    }
}
