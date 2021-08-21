package com.chicorski.chicofoodapi.api.openapi.controller;

import com.chicorski.chicofoodapi.api.exceptionHandler.Problem;
import com.chicorski.chicofoodapi.api.model.PedidoModel;
import com.chicorski.chicofoodapi.api.model.PedidoResumoModel;
import com.chicorski.chicofoodapi.api.model.input.PedidoInput;
import com.chicorski.chicofoodapi.domain.filter.PedidoFilter;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na reposta separados por virgula",
                    name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisa os pedidos")
    Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na reposta separados por virgula",
                    name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Busca um pedido por código")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    PedidoModel buscar(String codigo);

    @ApiOperation("Registra um pedido")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pedido registrado"),
    })
    PedidoModel adicionar(PedidoInput pedidoInput);
}
