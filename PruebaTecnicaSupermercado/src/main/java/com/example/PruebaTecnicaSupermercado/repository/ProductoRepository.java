package com.example.PruebaTecnicaSupermercado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.PruebaTecnicaSupermercado.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
     @Query("SELECT p FROM Producto p Where p.nombre=:nombre and p.precio=:precio")
     Optional<Producto> findByNombreAndPrecio(@Param("nombre") String nombre, @Param("precio") Double precio);

     Optional<Producto> findByNombre(String nombre);

}
