package com.chicorski.chicofoodapi.core.springfox;

import com.chicorski.chicofoodapi.api.model.CidadeModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Link;

import java.util.List;

@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApi {

    private CidadeEmbeddedModelOpenApi _embedded;
    private Link _links;

    @ApiModel("CidadesEmbeddedModel")
    @Data
    public class CidadeEmbeddedModelOpenApi {
        private List<CidadeModel> cidades;
    }
}
