package com.chicorski.chicofoodapi.domain.exception;

public class GrupoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public static final String MSG_GRUPO_NAO_ENCONTRADO = "Não existe um cadastro de grupo com o código %d";

    public GrupoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public GrupoNaoEncontradaException(Long id) {
        this(String.format(MSG_GRUPO_NAO_ENCONTRADO,id));
    }
}
