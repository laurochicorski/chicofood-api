package com.chicorski.chicofoodapi.domain.event;

import com.chicorski.chicofoodapi.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {

    private Pedido pedido;

}
