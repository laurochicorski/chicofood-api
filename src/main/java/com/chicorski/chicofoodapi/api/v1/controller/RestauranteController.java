package com.chicorski.chicofoodapi.api.v1.controller;

import com.chicorski.chicofoodapi.api.v1.assembler.RestauranteApenasNomeModelAssembler;
import com.chicorski.chicofoodapi.api.v1.assembler.RestauranteBasicoModelAssembler;
import com.chicorski.chicofoodapi.api.v1.assembler.RestauranteInputDisassembler;
import com.chicorski.chicofoodapi.api.v1.assembler.RestauranteModelAssembler;
import com.chicorski.chicofoodapi.api.v1.model.RestauranteModel;
import com.chicorski.chicofoodapi.api.v1.model.input.RestauranteInput;
import com.chicorski.chicofoodapi.api.v1.openapi.controller.RestauranteControllerOpenApi;
import com.chicorski.chicofoodapi.core.security.CheckSecurity;
import com.chicorski.chicofoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.chicorski.chicofoodapi.domain.exception.NegocioException;
import com.chicorski.chicofoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.chicorski.chicofoodapi.domain.model.Restaurante;
import com.chicorski.chicofoodapi.domain.repository.CozinhaRepository;
import com.chicorski.chicofoodapi.domain.repository.RestauranteRepository;
import com.chicorski.chicofoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @Autowired
    private RestauranteBasicoModelAssembler restauranteBasicoModelAssembler;

    @Autowired
    private RestauranteApenasNomeModelAssembler restauranteApenasNomeModelAssembler;

    @GetMapping
    public CollectionModel<RestauranteModel> listar(){
        List<Restaurante> restaurantes = restauranteRepository.findAll();

        return restauranteModelAssembler.toCollectionModel(restaurantes);
    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(params = "projecao=apenas-nome")
    public CollectionModel<RestauranteModel> listarApenasNome(){
        return listar();
    }

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping("/{id}")
    public RestauranteModel buscar(@PathVariable Long id) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(id);

        return restauranteModelAssembler.toModel(restaurante);
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);

            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping("/{id}")
    public RestauranteModel atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput) {
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(id);

        restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
        try {
            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        cadastroRestaurante.ativar(id);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try{
            cadastroRestaurante.ativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @DeleteMapping("/desativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try{
            cadastroRestaurante.inativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @DeleteMapping("/{id}/inativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        cadastroRestaurante.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
        cadastroRestaurante.abrir(restauranteId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
        cadastroRestaurante.fechar(restauranteId);

        return ResponseEntity.noContent().build();
    }
}
