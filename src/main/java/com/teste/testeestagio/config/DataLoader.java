package com.teste.testeestagio.config;

import com.teste.testeestagio.service.ImportacaoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ImportacaoService importacaoService;

    public DataLoader(ImportacaoService importacaoService) {
        this.importacaoService = importacaoService;
    }

    @Override
    public void run(String... args) {
        importacaoService.importarUsuarios();
    }
}
