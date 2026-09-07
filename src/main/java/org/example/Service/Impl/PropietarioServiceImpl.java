package org.example.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.Entity.HistoriaClinica;
import org.example.Entity.Mascota;
import org.example.Entity.Propietario;
import org.example.Exception.ResourceNotFoundException;
import org.example.Repository.HistoriaClinicaRepository;
import org.example.Repository.MascotaRepository;
import org.example.Repository.PropietarioRepository;
import org.example.Service.PropietarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropietarioServiceImpl implements PropietarioService {

    private final PropietarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Propietario> listarTodas() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Propietario buscarPorId (Long id){
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Historia clínica no encontrada"
                        ));
    }



}
