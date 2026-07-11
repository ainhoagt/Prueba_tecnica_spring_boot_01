package com.example.PruebaTecnicaSupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PruebaTecnicaSupermercado.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
