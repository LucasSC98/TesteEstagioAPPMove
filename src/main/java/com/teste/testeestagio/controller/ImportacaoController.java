package com.teste.testeestagio.controller;

import com.teste.testeestagio.service.ImportacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/importacao")
public class ImportacaoController {

    private final ImportacaoService importacaoService;

    @Autowired
    public ImportacaoController(ImportacaoService importacaoService) {
        this.importacaoService = importacaoService;
    }

    @PostMapping("/usuarios")
    public String importarUsuarios() {
        importacaoService.importarUsuarios();
        return "Importação concluída!";
    }
}
