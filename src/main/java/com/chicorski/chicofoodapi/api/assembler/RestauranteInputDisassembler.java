package com.chicorski.chicofoodapi.api.assembler;

import com.chicorski.chicofoodapi.api.model.input.RestauranteInput;
import com.chicorski.chicofoodapi.domain.model.Cidade;
import com.chicorski.chicofoodapi.domain.model.Cozinha;
import com.chicorski.chicofoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
//      Para evitar org.hibernate.HibernateException: identifier of an instance of
//      com.chicofood.domain.model.Cozinha was altered from 1 of 2
        restaurante.setCozinha(new Cozinha());

        if (restaurante.getEndereco() != null) {
            restaurante.getEndereco().setCidade(new Cidade());
        }

        modelMapper.map(restauranteInput, restaurante);
    }
}
