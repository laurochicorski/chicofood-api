package com.chicorski.chicofoodapi.api.assembler;

import com.chicorski.chicofoodapi.api.model.GrupoModel;
import com.chicorski.chicofoodapi.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoModel toModel(Grupo grupo) {
        return modelMapper.map(grupo, GrupoModel.class);
    }

    public List<GrupoModel> toColelctionModel(Collection<Grupo> grupos) {
        return grupos.stream()
                .map(grupo -> modelMapper.map(grupo, GrupoModel.class))
                .collect(Collectors.toList());
    }
}
