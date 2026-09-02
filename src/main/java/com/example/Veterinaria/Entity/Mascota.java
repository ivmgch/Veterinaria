package com.example.Veterinaria.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "mascotas")
@Data
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private Double peso;

    // Relacion varias mascotas pertenecen a un propietario
    @ManyToOne
    @JoinColumn(name = "propietario_id", nullable = false)
    private Propietario propietario;

    // Relacion una mascota tiene una unica historia clinica
    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL)
    private HistoriaClinica historiaClinica;

    // Relacin una mascota puede ser atendida por varios veterinarios
    @ManyToMany
    @JoinTable(
            name = "mascota_veterinario",
            joinColumns = @JoinColumn(name = "mascota_id"),
            inverseJoinColumns = @JoinColumn(name = "veterinario_id")
    )
    private List<Veterinario> veterinarios;
}