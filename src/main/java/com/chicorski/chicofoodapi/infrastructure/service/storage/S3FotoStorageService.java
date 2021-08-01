package com.chicorski.chicofoodapi.infrastructure.service.storage;

import com.chicorski.chicofoodapi.domain.service.FotoStorageService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3FotoStorageService implements FotoStorageService {

    @Override
    public InputStream recuperar(String nomeArquivo) {
        return null;
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {

    }

    @Override
    public void remover(String nomeArquivo) {

    }
}
