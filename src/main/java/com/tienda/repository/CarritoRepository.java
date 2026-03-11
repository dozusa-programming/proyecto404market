package com.tienda.repository;

import com.tienda.domain.Carrito;
import com.tienda.domain.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    List<Carrito> findByUsuario(Usuario usuario);
    Optional<Carrito> findByUsuarioAndProducto_IdProducto(Usuario usuario, Integer idProducto);
    void deleteByUsuario(Usuario usuario);
    int countByUsuario(Usuario usuario);
}
