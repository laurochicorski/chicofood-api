package com.chicorski.chicofoodapi.api.v1.controller;

import com.chicorski.chicofoodapi.api.v1.assembler.PedidoModelAssembler;
import com.chicorski.chicofoodapi.api.v1.assembler.PedidoResumoModelAssembler;
import com.chicorski.chicofoodapi.api.v1.model.PedidoInputDisassembler;
import com.chicorski.chicofoodapi.api.v1.model.PedidoModel;
import com.chicorski.chicofoodapi.api.v1.model.PedidoResumoModel;
import com.chicorski.chicofoodapi.api.v1.model.input.PedidoInput;
import com.chicorski.chicofoodapi.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.chicorski.chicofoodapi.core.data.PageWrapper;
import com.chicorski.chicofoodapi.core.data.PageableTranslator;
import com.chicorski.chicofoodapi.core.security.ChicoFoodSecurity;
import com.chicorski.chicofoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.chicorski.chicofoodapi.domain.exception.NegocioException;
import com.chicorski.chicofoodapi.domain.model.Pedido;
import com.chicorski.chicofoodapi.domain.model.Usuario;
import com.chicorski.chicofoodapi.domain.filter.PedidoFilter;
import com.chicorski.chicofoodapi.domain.repository.PedidoRepository;
import com.chicorski.chicofoodapi.domain.service.EmissaoPedidoService;
import com.chicorski.chicofoodapi.infrastructure.repository.spec.PedidoSpecs;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

    @Autowired
    private ChicoFoodSecurity chicoFoodSecurity;

    @GetMapping
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro,
                                                   @PageableDefault(size=10) Pageable pageable) {
        Pageable pageableTraduzido = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(
                PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

        pedidosPage = new PageWrapper<>(pedidosPage,pageable);

        return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);
    }

    @GetMapping("/{codigo}")
    public PedidoModel buscar(@PathVariable String codigo) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigo);

        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(chicoFoodSecurity.getUsuarioId());

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = ImmutableMap.of("codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valor Total");

        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}
