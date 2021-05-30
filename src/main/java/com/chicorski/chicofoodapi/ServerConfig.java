package com.chicorski.chicofoodapi;

import com.chicorski.chicofoodapi.di.service.AtivicaoClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public AtivicaoClienteService ativicaoClienteService() {
        return new AtivicaoClienteService();
    }
}
