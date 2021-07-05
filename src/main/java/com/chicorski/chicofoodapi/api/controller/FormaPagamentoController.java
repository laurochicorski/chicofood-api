package com.chicorski.chicofoodapi.api.controller;

import com.chicorski.chicofoodapi.api.assembler.FormaPagamentoInputDisassembler;
import com.chicorski.chicofoodapi.api.assembler.FormaPagamentoModelAssembler;
import com.chicorski.chicofoodapi.api.model.FormaPagamentoModel;
import com.chicorski.chicofoodapi.api.model.input.FormaPagamentoInput;
import com.chicorski.chicofoodapi.domain.model.FormaPagamento;
import com.chicorski.chicofoodapi.domain.repository.FormaPagamentoRepository;
import com.chicorski.chicofoodapi.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @GetMapping
    public List<FormaPagamentoModel> listar() {
        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAll();

        return formaPagamentoModelAssembler.toCollectionModel(formasPagamento);
    }

    @GetMapping("/{id}")
    public FormaPagamentoModel buscar(@PathVariable Long id) {
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(id);

        return formaPagamentoModelAssembler.toModel(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);

        formaPagamento = cadastroFormaPagamento.salvar(formaPagamento);

        return formaPagamentoModelAssembler.toModel(formaPagamento);
    }

    @PutMapping("/{id}")
    public FormaPagamentoModel atualizar(@PathVariable Long id,
                                         @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(id);

        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        formaPagamentoAtual = cadastroFormaPagamento.salvar(formaPagamentoAtual);

        return formaPagamentoModelAssembler.toModel(formaPagamentoAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroFormaPagamento.excluir(id);
    }
}
