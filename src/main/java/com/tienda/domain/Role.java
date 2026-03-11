package com.tienda.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ts_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole;

    private String rol;
}
