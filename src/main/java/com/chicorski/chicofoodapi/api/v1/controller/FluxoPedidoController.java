package com.chicorski.chicofoodapi.api.v1.controller;

import com.chicorski.chicofoodapi.api.v1.openapi.controller.FluxoPedidoContrllerOpenApi;
import com.chicorski.chicofoodapi.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pedidos/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
public class FluxoPedidoController implements FluxoPedidoContrllerOpenApi {

    @Autowired
    private FluxoPedidoService fluxoPedido;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confimar(@PathVariable String codigo) {
        fluxoPedido.confirmar(codigo);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancelar(@PathVariable String codigo) {
        fluxoPedido.cancelar(codigo);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> entregar(@PathVariable String codigo) {
        fluxoPedido.entregar(codigo);

        return ResponseEntity.noContent().build();
    }
}
