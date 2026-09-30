package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.HistoriaClinica;
import com.example.Veterinaria.Service.HistoriaClinicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historias-clinicas")
@RequiredArgsConstructor
@Tag(name = "Historias Clínicas", description = "Operaciones para la gestión de historias clínicas de las mascotas")
public class HistoriaClinicaController {

    private final HistoriaClinicaService service;

    @GetMapping
    @Operation(summary = "Listar todas las historias clínicas", description = "Obtiene una lista con todas las historias clínicas registradas en el sistema")
    public ResponseEntity<List<HistoriaClinica>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar historia clínica por ID", description = "Obtiene los detalles de una historia clínica específica")
    public ResponseEntity<HistoriaClinica> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/mascota/{mascotaId}")
    @Operation(summary = "Crear historia clínica", description = "Crea una nueva historia clínica y la asocia a una mascota existente (solo puede haber una por mascota)")
    public ResponseEntity<HistoriaClinica> crear(@Valid @RequestBody HistoriaClinica historia, @PathVariable Long mascotaId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crear(historia, mascotaId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar historia clínica", description = "Modifica los datos de una historia clínica existente")
    public ResponseEntity<HistoriaClinica> actualizar(@PathVariable Long id, @Valid @RequestBody HistoriaClinica historia) {
        return ResponseEntity.ok(service.actualizar(id, historia));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar historia clínica", description = "Elimina una historia clínica del sistema por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content, ideal para borrados.
    }
}