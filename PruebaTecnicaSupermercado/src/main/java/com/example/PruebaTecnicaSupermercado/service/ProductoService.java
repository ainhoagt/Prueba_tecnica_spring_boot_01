package com.example.PruebaTecnicaSupermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.PruebaTecnicaSupermercado.dto.ProductoDTO;
import com.example.PruebaTecnicaSupermercado.repository.ProductoRepository;

public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public List<ProductoDTO> traerProductos() {
        return productoRepository.findAll().stream().map(Mappers::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO producto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO producto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminarProducto(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
