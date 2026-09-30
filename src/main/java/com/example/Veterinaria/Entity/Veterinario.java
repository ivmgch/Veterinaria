package com.example.Veterinaria.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veterinarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nombre;

    @Column(name = "tarjeta_profesional")
    private String tarjetaProfesional;

    private String especialidad;
    private String correo;

    // Lado inverso de la relación ManyToMany
    @ManyToMany(mappedBy = "veterinarios")
    @JsonIgnore
    @Schema(hidden = true) // Oculta este campo en el Swagger POST
    private List<Mascota> mascotas = new ArrayList<>();

}