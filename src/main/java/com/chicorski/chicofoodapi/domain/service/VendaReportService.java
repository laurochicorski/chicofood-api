package com.chicorski.chicofoodapi.domain.service;

import com.chicorski.chicofoodapi.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffSet);
}
