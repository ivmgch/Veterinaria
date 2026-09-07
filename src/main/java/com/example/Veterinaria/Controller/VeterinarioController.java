package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Service.VeterinarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinario")
@AllArgsConstructor
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @GetMapping("/listar")
    public List<Veterinario> listarTodos() {
        return veterinarioService.listarTodos();
    }

    @GetMapping("/buscar/{id}")
    public Veterinario buscarPorId(@PathVariable Long id) {
        return veterinarioService.buscarPorId(id);
    }

    @PostMapping("/guardar")
    public Veterinario guardar(@RequestBody Veterinario veterinario) {
        return veterinarioService.guardar(veterinario);
    }

    @PutMapping("/actualizar/{id}")
    public Veterinario actualizar(@PathVariable Long id, @RequestBody Veterinario veterinario) {
        return veterinarioService.actualizar(id, veterinario);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        veterinarioService.eliminar(id);
    }
}