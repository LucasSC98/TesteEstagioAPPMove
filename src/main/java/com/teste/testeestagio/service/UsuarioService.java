package com.teste.testeestagio.service;


import com.teste.testeestagio.model.Usuario;
import com.teste.testeestagio.repository.UsuarioRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepositorio usuarioRepository;

    public UsuarioService(UsuarioRepositorio usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario createUsuario(Usuario usuarioDto) {
        if (usuarioRepository.findByEmail(usuarioDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        Usuario usuario = new Usuario();
        usuario.setId(java.util.UUID.randomUUID().toString());
        usuario.setName(usuarioDto.getName());
        usuario.setEmail(usuarioDto.getEmail());
        return usuarioRepository.save(usuario);
    }

    public Page<Usuario> searchUsuarios(String name, String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return usuarioRepository.findByNameContainingAndEmailContaining(name, email, pageable);
    }
}


