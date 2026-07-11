package com.example.PruebaTecnicaSupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PruebaTecnicaSupermercado.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

}
