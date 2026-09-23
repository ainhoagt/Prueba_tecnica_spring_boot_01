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

import com.example.PruebaTecnicaSupermercado.dto.ProductoDTO;
import com.example.PruebaTecnicaSupermercado.service.IProductoService;



@RestController 
@RequestMapping ("/api/productos")
public class ProductoController {
    @Autowired 
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> traerProductos(){
        return ResponseEntity.ok(productoService.traerProductos());
    }
    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDto){
        ProductoDTO creado= productoService.crearProducto(productoDto);
        return ResponseEntity.created(URI.create("/api/productos"+ creado.getId())).body(creado);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDto) {
       return ResponseEntity.ok(productoService.actualizarProducto(id, productoDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
