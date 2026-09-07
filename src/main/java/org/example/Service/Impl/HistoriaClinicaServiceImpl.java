package org.example.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.Entity.HistoriaClinica;
import org.example.Entity.Mascota;
import org.example.Exception.ResourceNotFoundException;
import org.example.Repository.HistoriaClinicaRepository;
import org.example.Repository.MascotaRepository;
import org.example.Service.HistoriaClinicaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    private final HistoriaClinicaRepository repository;

    private final MascotaRepository mascotaRepository;


    @Override
    public List<HistoriaClinica> listarTodas() {

        return repository.findAll();
    }


    @Override
    public HistoriaClinica buscarPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Historia clínica no encontrada"
                        ));
    }


    @Override
    public HistoriaClinica crear(HistoriaClinica historia, Long mascotaId) {

        Mascota mascota = mascotaRepository
                .findById(mascotaId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Mascota no encontrada"
                        ));

        repository.findByMascotaId(mascotaId)
                .ifPresent(h -> {
                    throw new IllegalArgumentException(
                            "La mascota ya tiene una historia clínica"
                    );
                });

        historia.setMascota(mascota);

        return repository.save(historia);
    }


    @Override
    public HistoriaClinica actualizar(Long id, HistoriaClinica datos) {

        HistoriaClinica actual = buscarPorId(id);

        actual.setFechaApertura(datos.getFechaApertura());

        actual.setAntecedentes(datos.getAntecedentes());

        actual.setObservaciones(datos.getObservaciones());

        return repository.save(actual);
    }


    @Override
    public void eliminar(Long id) {

        repository.delete(buscarPorId(id));
    }
}