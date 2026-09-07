package org.example.Service;

import org.example.Entity.Veterinario;

import java.util.List;

public interface VeterinarioService {
    List<Veterinario> listarTodas();

    Veterinario buscarPorId(Long id);

    Veterinario crear(Veterinario veterinario  , Long mascotaid);

    Veterinario actualizar(Long id, Veterinario veterinario);

    void eliminar(Long id);
}
