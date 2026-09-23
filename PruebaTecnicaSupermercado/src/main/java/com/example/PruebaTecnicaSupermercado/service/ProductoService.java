package com.example.PruebaTecnicaSupermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PruebaTecnicaSupermercado.dto.ProductoDTO;
import com.example.PruebaTecnicaSupermercado.exception.NotFoundException;
import com.example.PruebaTecnicaSupermercado.mappers.Mapper;
import com.example.PruebaTecnicaSupermercado.model.Producto;
import com.example.PruebaTecnicaSupermercado.repository.ProductoRepository;
@Service

public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public List<ProductoDTO> traerProductos() {
        return productoRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDto) {
        var producto=Producto.builder()
        .nombre(productoDto.getNombre())
        .categoria(productoDto.getCategoria())
        .precio(productoDto.getPrecio())
        .cantidad(productoDto.getCantidad())
        .build();
        return Mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO producto) {
        //vamos a buscar si existe el producto
        Producto prod= productoRepository.findById(id).orElseThrow(()-> new NotFoundException("Producto no encontrado"));
        prod.setNombre(producto.getNombre());
        prod.setCategoria(producto.getCategoria());
        prod.setPrecio(producto.getPrecio());
        prod.setCantidad(producto.getCantidad());
        return Mapper.toDTO(productoRepository.save(prod));
    }

    @Override
    public void eliminarProducto(Long id) {
        Producto prod= productoRepository.findById(id).orElseThrow(()-> new NotFoundException("Producto no encontrado"));
        productoRepository.delete(prod);
    }

}
