package com.chicorski.chicofoodapi.api.openapi.controller;

import com.chicorski.chicofoodapi.api.model.FormaPagamentoModel;
import com.chicorski.chicofoodapi.api.model.input.FormaPagamentoInput;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

@Api(tags = "Formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

    ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request);

    ResponseEntity<FormaPagamentoModel> buscar(Long id, ServletWebRequest request);

    FormaPagamentoModel adicionar(FormaPagamentoInput formaPagamentoInput);

    FormaPagamentoModel atualizar(Long id, FormaPagamentoInput formaPagamentoInput);

    void remover(Long id);
}
