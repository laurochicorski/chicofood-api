package com.chicorski.chicofoodapi.core.modelmapper;

import com.chicorski.chicofoodapi.api.model.EnderecoModel;
import com.chicorski.chicofoodapi.api.model.RestauranteModel;
import com.chicorski.chicofoodapi.domain.model.Endereco;
import com.chicorski.chicofoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

//        modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//                .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

        var enderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

        enderecoModelTypeMap.<String>addMapping(src -> src.getCidade().getEstado().getNome(), (dest, value) -> dest.getCidade().setEstado(value));

        return modelMapper;
    }
}
