package com.tienda.repository;

import com.tienda.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByUsernameAndToken(String username, String token);
    boolean existsByUsername(String username);
    boolean existsByCorreo(String correo);
}
