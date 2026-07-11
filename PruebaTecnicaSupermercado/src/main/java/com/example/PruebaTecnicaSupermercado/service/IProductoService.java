package com.example.PruebaTecnicaSupermercado.service;

import java.util.List;

import com.example.PruebaTecnicaSupermercado.dto.ProductoDTO;

public interface IProductoService {

    List<ProductoDTO> traerProductos();
    ProductoDTO crearProducto(ProductoDTO producto);
    ProductoDTO actualizarProducto(Long id, ProductoDTO producto);
    void eliminarProducto(Long id);
}
