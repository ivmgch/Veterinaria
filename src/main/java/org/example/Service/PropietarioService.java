package org.example.Service;

import org.example.Entity.Propietario;

import java.util.List;

public interface PropietarioService {
    List<Propietario> listarTodas();

    Propietario buscarPorId(Long id);

    Propietario crear(Propietario propietario, Long mascotaid);

    Propietario actualizar(Long id, Propietario propietario);

    void eliminar(Long id);
}
