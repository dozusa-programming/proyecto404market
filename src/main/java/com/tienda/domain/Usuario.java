package com.tienda.domain;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "ts_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String username;
    private String password;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private boolean activo;
    private String token;
    private String rutaImagen;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "ts_usuario_role",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private List<Role> roles;
}
