package com.tienda.controller;

import com.tienda.service.CarritoService;
import java.security.Principal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CarritoAdvice {

    private final CarritoService carritoService;

    public CarritoAdvice(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @ModelAttribute
    public void agregarContadorCarrito(Model model, Principal principal) {
        if (principal != null) {
            int cantidad = carritoService.contarItems(principal.getName());
            model.addAttribute("cantidadCarrito", cantidad);
        } else {
            model.addAttribute("cantidadCarrito", 0);
        }
    }
}

