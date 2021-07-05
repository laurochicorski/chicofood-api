package com.chicorski.chicofoodapi.domain.service;

import com.chicorski.chicofoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.chicorski.chicofoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.chicorski.chicofoodapi.domain.model.Cidade;
import com.chicorski.chicofoodapi.domain.model.Cozinha;
import com.chicorski.chicofoodapi.domain.model.Restaurante;
import com.chicorski.chicofoodapi.domain.repository.CozinhaRepository;
import com.chicorski.chicofoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroRestauranteService {

    public static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe cozinha com o id: %d";
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
        restaurante.setCozinha(cozinha);

        Long cidadeId = restaurante.getEndereco().getCidade().getId();
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
        restaurante.getEndereco().setCidade(cidade);

        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public void ativar(Long id) {
        Restaurante restauranteAtual = buscarOuFalhar(id);

        restauranteAtual.ativar();
    }

    @Transactional
    public void inativar(Long id) {
        Restaurante restauranteAtual = buscarOuFalhar(id);

        restauranteAtual.inativar();
    }

    public Restaurante buscarOuFalhar(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
    }


}
