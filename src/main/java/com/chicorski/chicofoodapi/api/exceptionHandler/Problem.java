package com.chicorski.chicofoodapi.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400")
    private Integer status;
    private String type;
    @ApiModelProperty(example = "Recurso não encontrado")
    private String title;
    @ApiModelProperty(example = "O recurso /cidfades, que você tentou acessar, é inexistente.")
    private String detail;
    @ApiModelProperty(example = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.")
    private String userMessage;
    @ApiModelProperty(example = "2021-08-20T03:31:53.215008Z")
    private OffsetDateTime timestamp;
    @ApiModelProperty("Lista objetos ou campos que geraram o erro (opcional)")
    private List<Object> objects;

    @ApiModel("ObjetoProblema")
    @Getter
    @Builder
    public static class Object {
        @ApiModelProperty(example = "preço")
        private String name;
        @ApiModelProperty(example = "preço é obrigatório")
        private String userMessage;
    }
}
