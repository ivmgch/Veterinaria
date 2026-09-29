package com.example.Veterinaria.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "propietarios")
@Data
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String documento;
    private String telefono;
    private String correo;

    // Relacion un propietario tiene Varias mascotas
    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Mascota> mascotas;

}