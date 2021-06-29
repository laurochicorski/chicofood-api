package com.chicorski.chicofoodapi;

import com.chicorski.chicofoodapi.domain.model.Cozinha;
import com.chicorski.chicofoodapi.domain.repository.CozinhaRepository;
import com.chicorski.chicofoodapi.util.DatabaseCleaner;
import com.chicorski.chicofoodapi.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private int quantidadeCozinhas;

    private String jsonCorretoCozinhaChinesa;

    private Cozinha cozinhaBrasileira;

    private static final int COZINHA_ID_INEXISTENTE = 100;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        databaseCleaner.clearTables();
        prepararDados();
    }


    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinha() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConterQuantidadeDeCozinhas_QuandoConsultarCozinha() {
        given()
                .accept(ContentType.JSON)
        .when()
                .get()
        .then()
                .body("", hasSize(quantidadeCozinhas));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinha() {
        jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(
                "/json/cozinha-chinesa.json");

        given()
                .body(jsonCorretoCozinhaChinesa)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
        given()
                .pathParam("id", cozinhaBrasileira.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(cozinhaBrasileira.getNome()));
    }

    @Test
    public void deveRetornarRespostaEStatus404_QuandoConsultarCozinhaInexistente() {
        given()
                .pathParam("id", COZINHA_ID_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados() {
        List<Cozinha> cozinhas = new ArrayList<>();
        
        Cozinha cozinhaTailandesa = new Cozinha();
        cozinhaTailandesa.setNome("Tailandesa");
        cozinhas.add(cozinhaTailandesa);

        cozinhaBrasileira = new Cozinha();
        cozinhaBrasileira.setNome("Brasileira");
        cozinhas.add(cozinhaBrasileira);

        Cozinha cozinha3 = new Cozinha();
        cozinha3.setNome("Italiana");
        cozinhas.add(cozinha3);

        salvarCozinhas(cozinhas);
        
    }

    private void salvarCozinhas(List<Cozinha> cozinhas) {
        quantidadeCozinhas = cozinhas.size();

        for (Cozinha cozinha :
                cozinhas) {
            cozinhaRepository.save(cozinha);
        }
    }

}
