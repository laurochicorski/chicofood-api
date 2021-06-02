package com.chicorski.chicofoodapi.jpa;

import com.chicorski.chicofoodapi.ChicofoodApiApplication;
import com.chicorski.chicofoodapi.domain.model.Cozinha;
import com.chicorski.chicofoodapi.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class BuscaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(ChicofoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        
        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
        
        Cozinha cozinha = cozinhaRepository.buscar(1L);

        System.out.println(cozinha.getNome());
    }
}
