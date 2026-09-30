package com.example.Veterinaria.Entity;

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
@Table(name = "mascotas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private Double peso;

    // Muchas Mascotas pertenecen a 1 Propietario
    @ManyToOne
    @JoinColumn(name = "propietario_id", nullable = false)
    @JsonIgnoreProperties("mascotas")
    private Propietario propietario;

    // 1 Mascota tiene 1 única Historia Clínica
    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("mascota")
    private HistoriaClinica historiaClinica;

    // Relación ManyToMany con Veterinarios
    @ManyToMany
    @JoinTable(
            name = "mascota_veterinario",
            joinColumns = @JoinColumn(name = "mascota_id"),
            inverseJoinColumns = @JoinColumn(name = "veterinario_id")
    )
    @JsonIgnoreProperties("mascotas") // Evita bucle circular en la serialización JSON
    @Schema(hidden = true)            // Oculta el campo en el Swagger POST
    private List<Veterinario> veterinarios = new ArrayList<>(); // <-- Inicialización salvadora

    // Metodo auxiliar para asegurar la relación bidireccional en el OneToOne
    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
        if (historiaClinica != null) {
            historiaClinica.setMascota(this);
        }
    }
}