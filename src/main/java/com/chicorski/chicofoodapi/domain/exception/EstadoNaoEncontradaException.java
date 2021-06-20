package com.chicorski.chicofoodapi.domain.exception;

public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException{

    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe um cadastro de estado com o código %d";

    public EstadoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoEncontradaException(Long id) {
        this(String.format(MSG_ESTADO_NAO_ENCONTRADO,id));
    }
}
