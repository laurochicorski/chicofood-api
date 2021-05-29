package com.chicorski.chicofoodapi;

import com.chicorski.chicofoodapi.di.notificacao.Notificador;
import com.chicorski.chicofoodapi.di.service.AtivicaoClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public AtivicaoClienteService ativicaoClienteService(Notificador notificador) {
        return new AtivicaoClienteService(notificador);
    }
}
