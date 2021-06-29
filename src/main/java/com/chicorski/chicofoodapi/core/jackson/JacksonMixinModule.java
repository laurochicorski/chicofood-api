package com.chicorski.chicofoodapi.core.jackson;

import com.chicorski.chicofoodapi.api.model.mixin.CidadeMixin;
import com.chicorski.chicofoodapi.api.model.mixin.CozinhaMixin;
import com.chicorski.chicofoodapi.api.model.mixin.RestauranteMixin;
import com.chicorski.chicofoodapi.domain.model.Cidade;
import com.chicorski.chicofoodapi.domain.model.Cozinha;
import com.chicorski.chicofoodapi.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1l;

    public JacksonMixinModule() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);

    }
}
