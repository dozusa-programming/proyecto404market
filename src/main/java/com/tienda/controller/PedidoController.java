package com.tienda.controller;

import com.tienda.domain.Pedido;
import com.tienda.service.PedidoService;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pago")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // Formulario de pago — muestra opciones TARJETA / ENTREGA
    @GetMapping
    public String formulario() {
        return "pago/index";
    }

    // Confirmar pedido
    @PostMapping("/confirmar")
    public String confirmar(@RequestParam String metodoPago,
                            Principal principal,
                            Model model) {
        Pedido pedido = pedidoService.confirmar(principal.getName(), metodoPago);
        model.addAttribute("pedido", pedido);
        return "pago/confirmacion";
    }
    @GetMapping("/mis-pedidos")
    public String misPedidos(Model model, Principal principal) {
        model.addAttribute("pedidos", pedidoService.obtenerPedidos(principal.getName()));
        return "pago/misPedidos";
    }

}
