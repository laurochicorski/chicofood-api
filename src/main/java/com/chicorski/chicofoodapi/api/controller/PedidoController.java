package com.chicorski.chicofoodapi.api.controller;

import com.chicorski.chicofoodapi.api.assembler.PedidoModelAssembler;
import com.chicorski.chicofoodapi.api.assembler.PedidoResumoModelAssembler;
import com.chicorski.chicofoodapi.api.model.PedidoInputDisassembler;
import com.chicorski.chicofoodapi.api.model.PedidoModel;
import com.chicorski.chicofoodapi.api.model.PedidoResumoModel;
import com.chicorski.chicofoodapi.api.model.input.PedidoInput;
import com.chicorski.chicofoodapi.core.data.PageableTranslator;
import com.chicorski.chicofoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.chicorski.chicofoodapi.domain.exception.NegocioException;
import com.chicorski.chicofoodapi.domain.model.Pedido;
import com.chicorski.chicofoodapi.domain.model.Usuario;
import com.chicorski.chicofoodapi.domain.filter.PedidoFilter;
import com.chicorski.chicofoodapi.domain.repository.PedidoRepository;
import com.chicorski.chicofoodapi.domain.service.EmissaoPedidoService;
import com.chicorski.chicofoodapi.infrastructure.repository.spec.PedidoSpecs;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

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

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na reposta separados por virgula",
            name = "campos", paramType = "query", type = "string")
    })
    @GetMapping
    public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro,
                                             @PageableDefault(size=10) Pageable pageable) {
        pageable = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);

        List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler.toCollectionModel(pedidosPage.getContent());

        return new PageImpl<PedidoResumoModel>(pedidosModel, pageable, pedidosPage.getTotalElements());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na reposta separados por virgula",
                    name = "campos", paramType = "query", type = "string")
    })
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

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

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
