package com.example.Veterinaria.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 10)
    @Column(name = "nombre", nullable = false)

    private String nombre;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)

    private String correo;

    @Min(18)

    private Integer edad;
}

