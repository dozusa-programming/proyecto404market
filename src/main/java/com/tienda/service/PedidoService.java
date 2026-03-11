package com.tienda.service;

import com.tienda.domain.*;
import com.tienda.repository.*;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final DetallePedidoRepository detalleRepo;
    private final CarritoRepository carritoRepo;
    private final UsuarioRepository usuarioRepo;

    public PedidoService(PedidoRepository pedidoRepo,
                         DetallePedidoRepository detalleRepo,
                         CarritoRepository carritoRepo,
                         UsuarioRepository usuarioRepo) {
        this.pedidoRepo  = pedidoRepo;
        this.detalleRepo = detalleRepo;
        this.carritoRepo = carritoRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Transactional
    public Pedido confirmar(String username, String metodoPago) {
        Usuario u = usuarioRepo.findByUsername(username).orElseThrow();
        List<Carrito> items = carritoRepo.findByUsuario(u);

        if (items.isEmpty()) throw new RuntimeException("El carrito está vacío");

        // Calcular totales
        double subtotal = items.stream()
            .mapToDouble(c -> c.getProducto().getPrecio().doubleValue() * c.getCantidad())
            .sum();
        double impuesto = subtotal * 0.13;

        // Crear pedido
        Pedido pedido = new Pedido();
        pedido.setUsuario(u);
        pedido.setSubtotal(subtotal);
        pedido.setImpuesto(impuesto);
        pedido.setTotal(subtotal + impuesto);
        pedido.setMetodoPago(metodoPago);
        pedidoRepo.save(pedido);

        // Crear detalles
        for (Carrito item : items) {
            DetallePedido d = new DetallePedido();
            d.setPedido(pedido);
            d.setProducto(item.getProducto());
            d.setCantidad(item.getCantidad());
            d.setPrecioUnitario(item.getProducto().getPrecio().doubleValue());
            d.setSubtotal(item.getProducto().getPrecio().doubleValue() * item.getCantidad());
            detalleRepo.save(d);
        }

        // Vaciar carrito
        carritoRepo.deleteByUsuario(u);

        return pedido;
    }

    public List<Pedido> obtenerPedidos(String username) {
        Usuario u = usuarioRepo.findByUsername(username).orElseThrow();
        return pedidoRepo.findByUsuarioOrderByFechaDesc(u);
    }
}
