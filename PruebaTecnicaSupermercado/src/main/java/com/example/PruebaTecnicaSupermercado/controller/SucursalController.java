package com.example.PruebaTecnicaSupermercado.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PruebaTecnicaSupermercado.dto.SucursalDTO;
import com.example.PruebaTecnicaSupermercado.service.ISucursalService;



@RestController 
@RequestMapping("/api/sucursales")
public class SucursalController {
    @Autowired 
    private ISucursalService sucursalService;

    @PostMapping("/crear")
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO sucursalDTO){
        SucursalDTO creado= sucursalService.crearSucursal(sucursalDTO);
        return ResponseEntity.created(URI.create("/api/sucursales"+creado.getId())).body(creado);
    }
    @GetMapping
    public ResponseEntity<List<SucursalDTO>> traerSucursales(){
        return ResponseEntity.ok(sucursalService.traerSucursales());
    }
    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> actualizarSucusal(@PathVariable Long id, @RequestBody SucursalDTO sucursalDTO){
        return ResponseEntity.ok(sucursalService.actualizarSucursal(id,sucursalDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id){
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
    
    

}
