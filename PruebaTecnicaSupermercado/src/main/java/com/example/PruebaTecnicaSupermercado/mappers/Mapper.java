package com.example.PruebaTecnicaSupermercado.mappers;

import com.example.PruebaTecnicaSupermercado.dto.ProductoDTO;
import com.example.PruebaTecnicaSupermercado.model.Producto;

public class Mapper {
    //Mapeo de Producto a ProductoDTO
    public static ProductoDTO toDTO(Producto producto){
        if(producto==null){
            return null;
        }
        return ProductoDTO.builder().id(producto.getId())
                .nombre(producto.getNombre())
                .precio(producto.getPrecio())
                .cantidad(producto.getCantidad())
                .build();
    }
    //Mapeo de Venta a VentaDTO
    //Mapeo de Sucursal a SucursalDTO
    
}
