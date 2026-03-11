package com.tienda.controller;

import com.tienda.domain.Usuario;
import com.tienda.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/registro/nuevo";
    }

    @PostMapping("/nuevo")
    public String registrar(Usuario usuario, Model model) {

        // Validar username duplicado
        if (usuarioService.existeUsername(usuario.getUsername())) {
            model.addAttribute("error",
                "El usuario '" + usuario.getUsername() + "' ya existe.");
            model.addAttribute("usuario", usuario);
            return "/registro/nuevo";
        }

        // Validar correo duplicado
        if (usuarioService.existeCorreo(usuario.getCorreo())) {
            model.addAttribute("error",
                "El correo '" + usuario.getCorreo() + "' ya está registrado.");
            model.addAttribute("usuario", usuario);
            return "/registro/nuevo";
        }

        usuarioService.registrar(usuario);
        return "redirect:/login?registrado=true";
    }
}
