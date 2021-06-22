package com.chicorski.chicofoodapi.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MENSAGEM_INCONPREENSIVEL("/mensagem-incompreensivel", "Mensagem inconpreensivel"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso" ),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro inválido");

    private String title;
    private String uri;

    ProblemType (String path, String title) {
        this.uri = "https://chicofood.com/" + path;
        this.title = title;
    }
}
