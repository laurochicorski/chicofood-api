package com.chicorski.chicofoodapi.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrupoInput {

    @NotBlank
    @ApiModelProperty(example = "Gerente", required = true)
    private String nome;
}
