package com.example.PruebaTecnicaSupermercado.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PruebaTecnicaSupermercado.dto.VentaDTO;
import com.example.PruebaTecnicaSupermercado.exception.NotFoundException;
import com.example.PruebaTecnicaSupermercado.mappers.Mapper;
import com.example.PruebaTecnicaSupermercado.model.DetalleVenta;
import com.example.PruebaTecnicaSupermercado.model.Producto;
import com.example.PruebaTecnicaSupermercado.model.Sucursal;
import com.example.PruebaTecnicaSupermercado.model.Venta;
import com.example.PruebaTecnicaSupermercado.repository.ProductoRepository;
import com.example.PruebaTecnicaSupermercado.repository.SucursalRepository;
import com.example.PruebaTecnicaSupermercado.repository.VentaRepository;
@Service
public class VentaService implements IVentaService {

    @Autowired 
    private VentaRepository ventaRepository;
    @Autowired 
    private ProductoRepository productoRepository;
    @Autowired 
    private SucursalRepository sucursalRepository;
    @Override
    public List<VentaDTO> traerVentas() {
        return ventaRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {
        //Validaciones
        if(ventaDto.getIdSucursal()==null) throw new RuntimeException("El id de la sucursal es obligatorio");
        if(ventaDto.getIdSucursal()==null) throw new RuntimeException("El id de la sucursal es obligatorio");
        if(ventaDto.getDetalles()==null || ventaDto.getDetalles().isEmpty()) throw new RuntimeException("La venta debe tener al menos un detalle");
        Sucursal sucursal= sucursalRepository.findById(ventaDto.getIdSucursal()).orElseThrow(()-> new NotFoundException("Sucursal no encontrada"));
         Venta venta=Venta.builder()
        .id(ventaDto.getId())
        .fecha(ventaDto.getFecha())
        .estado(ventaDto.getEstado())
        .total(ventaDto.getTotal())
        .sucursal(sucursal)
        .build();
        List<DetalleVenta> detalles=ventaDto.getDetalles().stream().map(det->{
            Producto p=productoRepository.findByNombreAndPrecio(det.getNombreProd(), det.getPrecio()).orElse(null);
            if(p==null){
                throw new NotFoundException("Producto no encontrado");
            }
            return DetalleVenta.builder()
            .id(det.getId())
            .cantProd(det.getCantProd())
            .precio(det.getSubtotal())
            .producto(p)
            .venta(venta)
            .build()
            ; 
        }).collect(Collectors.toList());

       venta.setDetalles(detalles);
        return Mapper.toDTO(ventaRepository.save(venta));
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
       Venta venta=ventaRepository.findById(id).orElseThrow(()-> new NotFoundException("Venta no encontrada"));
       venta.setFecha(ventaDto.getFecha());
         venta.setEstado(ventaDto.getEstado());
        venta.setTotal(ventaDto.getTotal());
        return Mapper.toDTO(ventaRepository.save(venta));
    }

    @Override
    public void eliminarVenta(Long id) {
        if(ventaRepository.existsById(id)){
            ventaRepository.deleteById(id);
        }else{
            throw new NotFoundException("Venta no encontrada");
        }
    }

}
