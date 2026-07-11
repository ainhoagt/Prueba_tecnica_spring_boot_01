package com.example.PruebaTecnicaSupermercado.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Venta")
public class Venta {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private String estado;
    private Double total;
    @ManyToOne 
    @JoinColumn(name="sucursal_id")
    private Sucursal sucursal;
    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detalles= new ArrayList<>();
}
