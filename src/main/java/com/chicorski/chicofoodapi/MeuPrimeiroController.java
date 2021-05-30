package com.chicorski.chicofoodapi;

import com.chicorski.chicofoodapi.di.modelo.Cliente;
import com.chicorski.chicofoodapi.di.service.AtivicaoClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    private AtivicaoClienteService ativicaoClienteService;

    public MeuPrimeiroController(AtivicaoClienteService ativicaoClienteService) {
        this.ativicaoClienteService = ativicaoClienteService;
    }

    @GetMapping("hello")
    @ResponseBody
    public String hello() {
        Cliente joao = new Cliente("João", "joao@xyz.com", "34999998888");
        ativicaoClienteService.ativar(joao);
        return "Ola3!";
    }
}
