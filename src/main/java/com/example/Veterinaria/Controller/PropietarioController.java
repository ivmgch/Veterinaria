package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.Propietario;
import com.example.Veterinaria.Service.PropietarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propietarios")
@RequiredArgsConstructor
@Tag(name = "Propietarios", description = "Operaciones para la gestión de los propietarios (dueños) de las mascotas")
public class PropietarioController {

    private final PropietarioService service;

    @GetMapping
    @Operation(summary = "Listar todos los propietarios", description = "Obtiene la lista completa de todos los propietarios registrados en la clínica")
    public ResponseEntity<List<Propietario>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar propietario por ID", description = "Obtiene los detalles específicos de un propietario según su ID")
    public ResponseEntity<Propietario> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo propietario", description = "Crea un nuevo registro de propietario en la base de datos")
    public ResponseEntity<Propietario> guardar(@Valid @RequestBody Propietario propietario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(propietario));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar información de propietario", description = "Modifica los datos personales de un propietario existente")
    public ResponseEntity<Propietario> actualizar(@PathVariable Long id, @Valid @RequestBody Propietario propietario) {
        return ResponseEntity.ok(service.actualizar(id, propietario));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar propietario", description = "Elimina un propietario del sistema (Nota: verificar reglas de negocio sobre mascotas asociadas antes de eliminar)")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}