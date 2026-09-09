package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Service.MascotaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascota")
@AllArgsConstructor
public class MascotaController {

    private final MascotaService mascotaService;

    @GetMapping("/buscar/{id}")
    public Mascota buscarPorId(@PathVariable Long id) {
        return mascotaService.buscarPorId(id);
    }

    @GetMapping("/propietario/{propietarioId}")
    public List<Mascota> buscarPorPropietario(@PathVariable Long propietarioId) {
        return mascotaService.buscarPorPropietario(propietarioId);
    }

    @PostMapping("/guardar/{propietarioId}")
    public Mascota guardar(@RequestBody Mascota mascota, @PathVariable Long propietarioId) {
        return mascotaService.guardar(mascota, propietarioId);
    }

    @PutMapping("/actualizar/{id}")
    public Mascota actualizar(@PathVariable Long id, @RequestBody Mascota mascota) {
        return mascotaService.actualizar(id, mascota);
    }

    @PutMapping("/{mascotaId}/asignar-veterinario/{veterinarioId}")
    public Mascota asignarVeterinario(@PathVariable Long mascotaId, @PathVariable Long veterinarioId) {
        return mascotaService.asignarVeterinario(mascotaId, veterinarioId);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        mascotaService.eliminar(id);
    }
}