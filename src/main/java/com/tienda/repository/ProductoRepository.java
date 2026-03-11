package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    //Consulta Derivada
    public List<Producto> findByActivoTrue();    
    
    //Consulta Derivada que filtra productos de un rango de precios Ordenado ascedentemente
    public List<Producto> findByPrecioBetweenOrderByPrecioAsc(double precioInf, double precioSup);
    
    //Consulta JPQL que filtra productos de un rango de precios Ordenado ascedentemente
    @Query(value="SELECT p FROM Producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaJPQL(double precioInf, double precioSup);
    
    //Consulta SQL que filtra productos de un rango de precios Ordenado ascedentemente
    @Query(nativeQuery = true,
           value = "SELECT * FROM producto p WHERE p.precio BETWEEN :precioInf AND :precioSup ORDER BY p.precio ASC")
    public List<Producto> consultaSQL(double precioInf, double precioSup);
    
    //Consulta Derivada que busca productos con precio menor al valor indicado
    public List<Producto> findByPrecioLessThanOrderByPrecioAsc(double precio);
    List<Producto> findByDescripcionContainingIgnoreCaseAndActivoTrue(String descripcion);

}
