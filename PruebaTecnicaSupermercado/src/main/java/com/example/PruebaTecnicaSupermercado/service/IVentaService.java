package com.example.PruebaTecnicaSupermercado.service;

import java.util.List;

import com.example.PruebaTecnicaSupermercado.dto.VentaDTO;

public interface IVentaService {
    List<VentaDTO> traerVentas();
    VentaDTO crearVenta(VentaDTO venta);
    VentaDTO actualizarVenta(Long id, VentaDTO venta);
    void eliminarVenta(Long id);

}
