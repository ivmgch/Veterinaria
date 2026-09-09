package com.example.Veterinaria.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "veterinarios")
@Data
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tarjetaProfesional;
    private String especialidad;
    private String correo;

    // Relacion un veterinario atiende a varias mascotas
    @ManyToMany(mappedBy = "veterinarios")
    @JsonIgnore
    private List<Mascota> mascotas;
}