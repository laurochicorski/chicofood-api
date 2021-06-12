package com.chicorski.chicofoodapi.api.controller;

import com.chicorski.chicofoodapi.domain.model.Cozinha;
import com.chicorski.chicofoodapi.domain.model.Restaurante;
import com.chicorski.chicofoodapi.domain.repository.CozinhaRepository;
import com.chicorski.chicofoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> unicaCozinhaPorNome(String nome) {
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/cozinhas/todas-por-nome")
    public List<Cozinha> todasCozinhaPorNome(String nome) {
        return cozinhaRepository.findByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFre(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/por-nome-e-cozinha")
    public List<Restaurante> restaurantesPorNomeECozinha(String nome, Long cozinhaId) {
        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
    }
}
