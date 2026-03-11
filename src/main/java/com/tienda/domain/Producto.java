package com.tienda.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name="ts_producto")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Integer idProducto;
    
    @Column(unique = true, nullable = false, length = 50)
    @NotNull
    @Size(max=50)
    private String descripcion;
    private String detalle;
    
    @Column(precision = 12, scale = 2)
    @NotNull(message = "El precio no puede estar vacio")
    @DecimalMin(value = "0.1", inclusive = true, message = "El precio debe de ser igual o superior a 0")
    private BigDecimal precio;
    
    @NotNull(message = "Las existencias no pueden estar definidas")
    @Min(value = 0, message = "Las existencias deben de ser igual o superior a 0")
    private Integer existencias;
    
    @Column(length = 1024)
    @Size(max=1024)
    private String rutaImagen;
    private boolean activo;
    
    @ManyToOne
    @JoinColumn (name="id_categoria")
    private Categoria categoria;
}
