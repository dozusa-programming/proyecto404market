package com.tienda.repository;

import com.tienda.domain.Pedido;
import com.tienda.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByUsuarioOrderByFechaDesc(Usuario usuario);
}
