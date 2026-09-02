package com.example.Veterinaria.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "historias_clinicas")
@Data
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaApertura;
    private String antecedentes;
    private String observaciones;

    // Relacion una historia clínica pertenece a una única mascota
    @OneToOne
    @JoinColumn(name = "mascota_id", unique = true, nullable = false)
    private Mascota mascota;

}