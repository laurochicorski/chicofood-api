package com.chicorski.chicofoodapi.core.modelmapper;

import com.chicorski.chicofoodapi.api.v1.model.EnderecoModel;
import com.chicorski.chicofoodapi.api.v1.model.input.ItemPedidoInput;
import com.chicorski.chicofoodapi.api.v2.model.input.CidadeInputV2;
import com.chicorski.chicofoodapi.domain.model.Cidade;
import com.chicorski.chicofoodapi.domain.model.Endereco;
import com.chicorski.chicofoodapi.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class)
                .addMappings(mapper -> mapper.skip(Cidade::setId));

//        modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//                .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

        var enderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);

        enderecoModelTypeMap.<String>addMapping(src -> src.getCidade().getEstado().getNome(), (dest, value) -> dest.getCidade().setEstado(value));

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        return modelMapper;
    }
}
