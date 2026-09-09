package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.HistoriaClinica;
import com.example.Veterinaria.Service.HistoriaClinicaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historia")
@AllArgsConstructor
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaService;

    @GetMapping("/listar")
    public List<HistoriaClinica> listarTodas() {
        return historiaService.listarTodas();
    }

    @GetMapping("/buscar/{id}")
    public HistoriaClinica buscarPorId(@PathVariable Long id) {
        return historiaService.buscarPorId(id);
    }

    @PostMapping("/crear/{mascotaId}")
    public HistoriaClinica crear(@RequestBody HistoriaClinica historia, @PathVariable Long mascotaId) {
        return historiaService.crear(historia, mascotaId);
    }

    @PutMapping("/actualizar/{id}")
    public HistoriaClinica actualizar(@PathVariable Long id, @RequestBody HistoriaClinica historia) {
        return historiaService.actualizar(id, historia);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        historiaService.eliminar(id);
    }
}