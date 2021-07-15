package com.chicorski.chicofoodapi.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoException(String codigo) {
        super(String.format("Não existe um pedido com código %d", codigo));
    }
}