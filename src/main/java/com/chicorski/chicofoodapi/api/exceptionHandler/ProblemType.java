package com.chicorski.chicofoodapi.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MENSAGEM_INCONPREENSIVEL("/mensagem-incompreensivel", "Mensagem inconpreensivel"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso" ),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro inválido"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos");

    private String title;
    private String uri;

    ProblemType (String path, String title) {
        this.uri = "https://chicofood.com" + path;
        this.title = title;
    }
}
