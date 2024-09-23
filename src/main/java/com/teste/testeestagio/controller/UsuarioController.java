package com.teste.testeestagio.controller;


import com.teste.testeestagio.dto.UsuarioDto;
import com.teste.testeestagio.model.Usuario;
import com.teste.testeestagio.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setName(usuarioDto.getName());
        usuario.setEmail(usuarioDto.getEmail());

        Usuario novoUsuario = usuarioService.createUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Usuario>> listarUsuarios(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<Usuario> usuarios = usuarioService.searchUsuarios(name, email, page, size);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("A aplicação está funcionando!");
    }
    

}

