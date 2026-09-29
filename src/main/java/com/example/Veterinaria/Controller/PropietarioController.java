package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.Propietario;
import com.example.Veterinaria.Service.PropietarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propietario")
@AllArgsConstructor
public class PropietarioController {

    private final PropietarioService propietarioService;

    @GetMapping("/listar")
    public List<Propietario> listarTodos() {
        return propietarioService.listarTodos();
    }

    @GetMapping("/buscar/{id}")
    public Propietario buscarPorId(@PathVariable Long id) {
        return propietarioService.buscarPorId(id);
    }

    @PostMapping("/guardar")
    public Propietario guardar(@RequestBody Propietario propietario) {
        return propietarioService.guardar(propietario);
    }

    @PutMapping("/actualizar/{id}")
    public Propietario actualizar(@PathVariable Long id, @RequestBody Propietario propietario) {
        return propietarioService.actualizar(id, propietario);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        propietarioService.eliminar(id);
    }
}