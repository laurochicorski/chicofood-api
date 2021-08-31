package com.chicorski.chicofoodapi.api.v1.openapi.model;

import com.chicorski.chicofoodapi.api.v1.model.CidadeModel;
import com.chicorski.chicofoodapi.core.springfox.CidadesModelOpenApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.util.List;

@ApiModel("CozinhasModel")
@Setter
@Getter
public class CozinhasModelOpenApi  {

    private CidadesModelOpenApi.CidadeEmbeddedModelOpenApi _embedded;
    private Link _links;
    private PageModelOpenApi page;

    @ApiModel("CozinhasEmbeddedModel")
    @Data
    public class CozinhaEmbeddedModelOpenApi {
        private List<CidadeModel> cozinhas;
    }

}
