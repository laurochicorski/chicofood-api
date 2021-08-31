package com.chicorski.chicofoodapi.api.v1.openapi.controller;

import com.chicorski.chicofoodapi.api.exceptionHandler.Problem;
import com.chicorski.chicofoodapi.api.v1.model.CidadeModel;
import com.chicorski.chicofoodapi.api.v1.model.input.CidadeInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation("Lista as cidades")
    CollectionModel<CidadeModel> listar();

    @ApiOperation("Busca cidade por id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class)
    })
    CidadeModel buscar(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long id);

    @ApiOperation("Inclui uma cidade por id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cidade cadastrada")
    })
    CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma cidade", example = "1", required = true) CidadeInput cidadeInput);

    @ApiOperation("Edita uma cidade por id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cidade atualizada"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    CidadeModel atualizar(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long id,
                          @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados.", example = "1", required = true) CidadeInput cidadeInput);

    @ApiOperation("Exclui uma cidade por id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cidade excluída"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    void remover(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long id);
}
