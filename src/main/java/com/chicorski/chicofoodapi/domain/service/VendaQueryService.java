package com.chicorski.chicofoodapi.domain.service;

import com.chicorski.chicofoodapi.domain.filter.VendaDiariaFilter;
import com.chicorski.chicofoodapi.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffSet);
}
