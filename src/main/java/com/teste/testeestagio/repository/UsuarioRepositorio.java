package com.teste.testeestagio.repository;

import com.teste.testeestagio.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, UUID> {

    Page<Usuario> findByNameContainingAndEmailContaining(String name, String email, Pageable pageable);

    Optional<Usuario> findByEmail(String email);


    @Query("SELECT u.email FROM Usuario u")
    List<String> findAllEmails();
}
