package com.chicorski.chicofoodapi.api.openapi.controller;

import com.chicorski.chicofoodapi.api.exceptionHandler.Problem;
import com.chicorski.chicofoodapi.api.model.PedidoModel;
import com.chicorski.chicofoodapi.api.model.PedidoResumoModel;
import com.chicorski.chicofoodapi.api.model.input.PedidoInput;
import com.chicorski.chicofoodapi.domain.filter.PedidoFilter;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na reposta separados por virgula",
                    name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Pesquisa os pedidos")
    PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na reposta separados por virgula",
                    name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Busca um pedido por código")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Pedido não encontrado", response = Problem.class)
    })
    PedidoModel buscar(@ApiParam(value = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55",
            required = true) String codigo);

    @ApiOperation("Registra um pedido")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pedido registrado"),
    })
    PedidoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true) PedidoInput pedidoInput);
}
