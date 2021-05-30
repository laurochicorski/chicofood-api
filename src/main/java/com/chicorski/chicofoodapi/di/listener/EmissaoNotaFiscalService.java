package com.chicorski.chicofoodapi.di.listener;

import com.chicorski.chicofoodapi.di.service.ClientAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {

    @EventListener
    public void clientAtivadoListener(ClientAtivadoEvent event){
        System.out.println("Emitindo nota fiscal para o cliente: " +event.getCliente().getNome());
    }
}
