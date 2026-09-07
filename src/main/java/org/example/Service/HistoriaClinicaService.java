package org.example.Service;

import org.example.Entity.HistoriaClinica;

import java.util.List;

public interface HistoriaClinicaService {
    List<HistoriaClinica> listarTodas();

    HistoriaClinica buscarPorId(Long id);

    HistoriaClinica crear(HistoriaClinica historia, Long mascotaid);

    HistoriaClinica actualizar(Long id, HistoriaClinica historia);

    void eliminar(Long id);

}
