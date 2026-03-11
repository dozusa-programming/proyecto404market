package com.tienda.controller;

import com.tienda.domain.Carrito;
import com.tienda.service.CarritoService;
import java.security.Principal;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping
    public String verCarrito(Model model, Principal principal) {
        List<Carrito> items = carritoService.obtenerCarrito(principal.getName());
        double subtotal = items.stream()
            .mapToDouble(c -> c.getProducto().getPrecio().doubleValue() * c.getCantidad())
            .sum();
        double impuesto = subtotal * 0.13;
        model.addAttribute("items", items);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("impuesto", impuesto);
        model.addAttribute("total", subtotal + impuesto);
        return "carrito/index";
    }

    @PostMapping("/agregar")
    public String agregar(@RequestParam Integer idProducto, Principal principal) {
        carritoService.agregar(principal.getName(), idProducto);
        return "redirect:/consultas/listado";
    }

    @PostMapping("/actualizar")
    public String actualizar(@RequestParam Integer idCarrito,
                             @RequestParam Integer cantidad,
                             Principal principal) {
        carritoService.actualizarCantidad(principal.getName(), idCarrito, cantidad);
        return "redirect:/carrito";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idCarrito, Principal principal) {
        carritoService.eliminar(principal.getName(), idCarrito);
        return "redirect:/carrito";
    }

    @PostMapping("/vaciar")
    public String vaciar(Principal principal) {
        carritoService.vaciar(principal.getName());
        return "redirect:/carrito";
    }
}
