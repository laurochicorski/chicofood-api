package com.chicorski.chicofoodapi.jpa;

import com.chicorski.chicofoodapi.ChicofoodApiApplication;
import com.chicorski.chicofoodapi.domain.model.Cozinha;
import com.chicorski.chicofoodapi.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExclusaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(ChicofoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        
        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);

        cozinhaRepository.remover(cozinha);
    }
}
