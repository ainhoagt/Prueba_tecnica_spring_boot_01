package com.example.PruebaTecnicaSupermercado.service;

import java.util.List;

import com.example.PruebaTecnicaSupermercado.dto.SucursalDTO;

public interface ISucursalService {

    List<SucursalDTO> traerSucursales();
    SucursalDTO crearSucursal(SucursalDTO sucursal);
    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursal);
    void eliminarSucursal(Long id);
}
