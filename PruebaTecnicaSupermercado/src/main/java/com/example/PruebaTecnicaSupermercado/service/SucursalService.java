package com.example.PruebaTecnicaSupermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PruebaTecnicaSupermercado.dto.SucursalDTO;
import com.example.PruebaTecnicaSupermercado.exception.NotFoundException;
import com.example.PruebaTecnicaSupermercado.mappers.Mapper;
import com.example.PruebaTecnicaSupermercado.model.Sucursal;
import com.example.PruebaTecnicaSupermercado.repository.SucursalRepository;
@Service
public class SucursalService implements ISucursalService {

    @Autowired 
    private SucursalRepository sucursalRepository;
    @Override
    public List<SucursalDTO> traerSucursales() {
        return sucursalRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursal) {
        Sucursal sucursalEntity=Sucursal.builder()
        .id(sucursal.getId())
        .nombre(sucursal.getNombre())
        .direccion(sucursal.getDireccion())
        .build();
        return Mapper.toDTO(sucursalRepository.save(sucursalEntity));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto) {
        Sucursal sucursal=sucursalRepository.findById(id).orElseThrow(()-> new NotFoundException("Sucursal no encontrada"));
        sucursal.setNombre(sucursalDto.getNombre());
        sucursal.setDireccion(sucursalDto.getDireccion());
        return Mapper.toDTO(sucursalRepository.save(sucursal));
    }

    @Override
    public void eliminarSucursal(Long id) {
        if(sucursalRepository.existsById(id)){
            sucursalRepository.deleteById(id);
        }else{
            throw new NotFoundException("Sucursal no encontrada");
        }
    }

  

}
