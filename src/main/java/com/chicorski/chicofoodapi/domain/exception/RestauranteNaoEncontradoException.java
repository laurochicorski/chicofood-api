package com.chicorski.chicofoodapi.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{

    public static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Não existe um cadastro de restaurante com o código %d";

    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long id) {
        this(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO,id));
    }
}
