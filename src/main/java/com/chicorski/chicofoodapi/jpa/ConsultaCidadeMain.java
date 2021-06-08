package com.chicorski.chicofoodapi.jpa;

import com.chicorski.chicofoodapi.ChicofoodApiApplication;
import com.chicorski.chicofoodapi.domain.model.Cidade;
import com.chicorski.chicofoodapi.domain.model.Estado;
import com.chicorski.chicofoodapi.domain.repository.CidadeRepository;
import com.chicorski.chicofoodapi.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaCidadeMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(ChicofoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        
        CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
        
        List<Cidade> cidades = cidadeRepository.listar();

        for (Cidade cidade : cidades) {
            System.out.printf("%d - %s - %s\n", cidade.getId(), cidade.getNome(), cidade.getEstado().getNome());
        }
    }
}
