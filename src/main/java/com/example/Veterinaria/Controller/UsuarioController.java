package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.Usuario;
import com.example.Veterinaria.Service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor

public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/obtener")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/list/{id}")
    public Optional<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
}
