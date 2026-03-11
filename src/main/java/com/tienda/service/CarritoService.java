package com.tienda.service;

import com.tienda.domain.Carrito;
import com.tienda.domain.Producto;
import com.tienda.domain.Usuario;
import com.tienda.repository.CarritoRepository;
import com.tienda.repository.ProductoRepository;
import com.tienda.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepo;
    private final ProductoRepository productoRepo;
    private final UsuarioRepository usuarioRepo;

    public CarritoService(CarritoRepository carritoRepo,
                          ProductoRepository productoRepo,
                          UsuarioRepository usuarioRepo) {
        this.carritoRepo = carritoRepo;
        this.productoRepo = productoRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public List<Carrito> obtenerCarrito(String username) {
        Usuario u = usuarioRepo.findByUsername(username).orElseThrow();
        return carritoRepo.findByUsuario(u);
    }

    public int contarItems(String username) {
        Usuario u = usuarioRepo.findByUsername(username).orElseThrow();
        return carritoRepo.countByUsuario(u);
    }

    @Transactional
    public void agregar(String username, Integer idProducto) {
        Usuario u = usuarioRepo.findByUsername(username).orElseThrow();
        Optional<Carrito> existente = carritoRepo.findByUsuarioAndProducto_IdProducto(u, idProducto);
        if (existente.isPresent()) {
            Carrito c = existente.get();
            c.setCantidad(c.getCantidad() + 1);
            carritoRepo.save(c);
        } else {
            Producto p = productoRepo.findById(idProducto).orElseThrow();
            Carrito c = new Carrito();
            c.setUsuario(u);
            c.setProducto(p);
            c.setCantidad(1);
            carritoRepo.save(c);
        }
    }

    @Transactional
    public void actualizarCantidad(String username, Integer idCarrito, Integer cantidad) {
        Usuario u = usuarioRepo.findByUsername(username).orElseThrow();
        Carrito c = carritoRepo.findById(idCarrito).orElseThrow();
        if (c.getUsuario().getIdUsuario().equals(u.getIdUsuario())) {
            if (cantidad <= 0) {
                carritoRepo.delete(c);
            } else {
                c.setCantidad(cantidad);
                carritoRepo.save(c);
            }
        }
    }

    @Transactional
    public void eliminar(String username, Integer idCarrito) {
        Usuario u = usuarioRepo.findByUsername(username).orElseThrow();
        Carrito c = carritoRepo.findById(idCarrito).orElseThrow();
        if (c.getUsuario().getIdUsuario().equals(u.getIdUsuario())) {
            carritoRepo.delete(c);
        }
    }

    @Transactional
    public void vaciar(String username) {
        Usuario u = usuarioRepo.findByUsername(username).orElseThrow();
        carritoRepo.deleteByUsuario(u);
    }
}
