package com.chicorski.chicofoodapi.infrastructure.service.report;

import com.chicorski.chicofoodapi.domain.filter.VendaDiariaFilter;
import com.chicorski.chicofoodapi.domain.service.VendaReportService;
import org.springframework.stereotype.Service;

@Service
public class PdfVendaReportService implements VendaReportService {


    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffSet) {
        return null;
    }
}
