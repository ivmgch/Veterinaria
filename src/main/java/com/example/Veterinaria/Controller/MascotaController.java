package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Service.MascotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
@Tag(name = "Mascotas", description = "Operaciones para la gestión y asignación de mascotas en la clínica")
public class MascotaController {

    private final MascotaService service;

    @GetMapping
    @Operation(summary = "Listar todas las mascotas", description = "Obtiene el listado completo de todas las mascotas registradas")
    public ResponseEntity<List<Mascota>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar mascota por ID", description = "Obtiene los detalles específicos de una mascota según su ID")
    public ResponseEntity<Mascota> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/propietario/{propietarioId}")
    @Operation(summary = "Buscar mascotas por propietario", description = "Retorna una lista de las mascotas que pertenecen a un propietario específico")
    public ResponseEntity<List<Mascota>> buscarPorPropietario(@PathVariable Long propietarioId) {
        return ResponseEntity.ok(service.buscarPorPropietario(propietarioId));
    }

    @PostMapping("/propietario/{propietarioId}")
    @Operation(summary = "Registrar nueva mascota", description = "Crea el registro de una mascota y la asocia a un propietario existente")
    public ResponseEntity<Mascota> guardar(@Valid @RequestBody Mascota mascota, @PathVariable Long propietarioId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(mascota, propietarioId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar información de mascota", description = "Modifica los datos de una mascota ya registrada en el sistema")
    public ResponseEntity<Mascota> actualizar(@PathVariable Long id, @Valid @RequestBody Mascota mascota) {
        return ResponseEntity.ok(service.actualizar(id, mascota));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar mascota", description = "Elimina de forma permanente el registro de una mascota por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{mascotaId}/veterinarios/{veterinarioId}")
    @Operation(summary = "Asignar veterinario a mascota", description = "Relaciona a un veterinario existente para que atienda a una mascota específica")
    public ResponseEntity<Mascota> asignarVeterinario(@PathVariable Long mascotaId, @PathVariable Long veterinarioId) {
        return ResponseEntity.ok(service.asignarVeterinario(mascotaId, veterinarioId));
    }
}