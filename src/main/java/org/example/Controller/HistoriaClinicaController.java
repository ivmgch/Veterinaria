package org.example.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.Entity.HistoriaClinica;
import org.example.Service.HistoriaClinicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historias")
@RequiredArgsConstructor
public class HistoriaClinicaController {

    private final HistoriaClinicaService service;


    @GetMapping
    public List<HistoriaClinica> listar() {

        return service.listarTodas();
    }


    @GetMapping("/{id}")
    public HistoriaClinica buscar(@PathVariable Long id) {

        return service.buscarPorId(id);
    }


    @PostMapping("/mascota/{mascotaId}")
    public ResponseEntity<HistoriaClinica> crear(@PathVariable Long mascotaId, @Valid @RequestBody HistoriaClinica historia) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        service.crear(historia, mascotaId));
    }


    @PutMapping("/{id}")
    public HistoriaClinica actualizar(@PathVariable Long id, @Valid @RequestBody HistoriaClinica historia) {

        return service.actualizar(id, historia);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
