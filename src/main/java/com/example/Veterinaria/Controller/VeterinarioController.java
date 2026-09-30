package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Service.VeterinarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
@RequiredArgsConstructor
@Tag(name = "Veterinarios", description = "Operaciones relacionadas con la gestión de veterinarios y especialistas")
public class VeterinarioController {

    private final VeterinarioService service;

    @GetMapping
    @Operation(summary = "Listar todos los veterinarios", description = "Obtiene una lista de todos los veterinarios registrados en el sistema")
    public ResponseEntity<List<Veterinario>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar veterinario por ID", description = "Obtiene los detalles de un veterinario específico")
    public ResponseEntity<Veterinario> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo veterinario", description = "Registra un nuevo veterinario en la base de datos")
    public ResponseEntity<Veterinario> guardar(@Valid @RequestBody Veterinario veterinario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(veterinario));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar veterinario", description = "Actualiza los datos de un veterinario existente")
    public ResponseEntity<Veterinario> actualizar(@PathVariable Long id, @Valid @RequestBody Veterinario veterinario) {
        return ResponseEntity.ok(service.actualizar(id, veterinario));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar veterinario", description = "Elimina un veterinario del sistema por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}