package com.example.PruebaTecnicaSupermercado.mappers;

import java.util.stream.Collectors;

import com.example.PruebaTecnicaSupermercado.dto.DetalleVentaDTO;
import com.example.PruebaTecnicaSupermercado.dto.ProductoDTO;
import com.example.PruebaTecnicaSupermercado.dto.SucursalDTO;
import com.example.PruebaTecnicaSupermercado.dto.VentaDTO;
import com.example.PruebaTecnicaSupermercado.model.Producto;
import com.example.PruebaTecnicaSupermercado.model.Sucursal;
import com.example.PruebaTecnicaSupermercado.model.Venta;

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
    public static VentaDTO toDTO(Venta venta){
        if(venta==null){
            return null;
        }
        //Obtenemos los detalles de la venta y mapeamos a DetalleVentaDTO
        var detalle= venta.getDetalles().stream().map(det->
            DetalleVentaDTO.builder().id(det.getId())
            .nombreProd(det.getProducto().getNombre())
            .cantProd(det.getCantProd())
            .precio(det.getPrecio())
            //calculamos subtotal de la venta
            .subtotal(det.getPrecio()* det.getCantProd()).build()
        ).collect(Collectors.toList());

        //Calculamos el total de la venta sumando los subtotales de los detalles
        var total=detalle.stream().map(DetalleVentaDTO::getSubtotal).reduce(0.0,Double::sum);

        //Mapeamos venta a VentaDTO
        return VentaDTO.builder().id(venta.getId())
        .fecha(venta.getFecha())
        .estado(venta.getEstado())
        .idSucursal(venta.getSucursal().getId())
        .detalles(detalle)
        .total(total)
        .build();
        
    }
    //Mapeo de Sucursal a SucursalDTO
    public static SucursalDTO toDTO(Sucursal sucursal){
        if(sucursal==null){
            return null;
        }
        return SucursalDTO.builder().id(sucursal.getId())
                .nombre(sucursal.getNombre())
                .direccion(sucursal.getDireccion())
                .build();
    }
    
}
