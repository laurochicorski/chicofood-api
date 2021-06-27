package com.chicorski.chicofoodapi;

import com.chicorski.chicofoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.chicorski.chicofoodapi.domain.exception.EntidadeEmUsoException;
import com.chicorski.chicofoodapi.domain.model.Cozinha;
import com.chicorski.chicofoodapi.domain.service.CadastroCozinhaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationIT {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Test
	public void deveAtribuirIdQuandoCadastrarCozinhaComDadosCorretos() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		novaCozinha = cadastroCozinha.salvar(novaCozinha);

		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	@Test(expected = ConstraintViolationException.class)
	public void deveFalharQuandoCadastrarCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);

		novaCozinha = cadastroCozinha.salvar(novaCozinha);
	}

	@Test(expected = EntidadeEmUsoException.class)
	public void deveFalharQuandoExcluirCozinhaEmUso() {
		cadastroCozinha.excluir(1l);
	}

	@Test(expected = CozinhaNaoEncontradaException.class)
	public void deveFalharQuandoExcluirCozinhaInexistente() {
		cadastroCozinha.excluir(100l);
	}

}
