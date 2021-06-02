package com.chicorski.chicofoodapi.jpa;

import com.chicorski.chicofoodapi.ChicofoodApiApplication;
import com.chicorski.chicofoodapi.domain.model.Cozinha;
import com.chicorski.chicofoodapi.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(ChicofoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        
        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Japanesa");
        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Italiana");

        cozinha1 =cozinhaRepository.salvar(cozinha1);
        cozinha2 = cozinhaRepository.salvar(cozinha2);

        System.out.printf("%d = %s\n", cozinha1.getId(), cozinha1.getNome());
        System.out.printf("%d = %s\n", cozinha2.getId(), cozinha2.getNome());

    }
}
