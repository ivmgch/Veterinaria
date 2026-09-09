package com.example.Veterinaria.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "historias_clinicas")
@Data
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fechaApertura;
    private String antecedentes;
    private String observaciones;

    @OneToOne
    @JoinColumn(name = "mascota_id")
    @JsonIgnore
    private Mascota mascota;
}