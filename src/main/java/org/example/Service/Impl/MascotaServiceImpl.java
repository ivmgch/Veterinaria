package org.example.Service.Impl;

import org.example.Entity.Mascota;
import org.example.Entity.Propietario;
import org.example.Entity.Veterinario;
import org.example.Exception.ResourceNotFoundException;
import org.example.Repository.MascotaRepository;
import org.example.Repository.PropietarioRepository;
import org.example.Repository.VeterinarioRepository;
import org.example.Service.MascotaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;

    private final PropietarioRepository propietarioRepository;

    private final VeterinarioRepository veterinarioRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Mascota> listarTodas() {

        return mascotaRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Mascota buscarPorId(Long id) {

        return mascotaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Mascota no encontrada: " + id
                        ));
    }


    @Override
    @Transactional
    public Mascota guardar(Mascota mascota, Long propietarioId) {

        Propietario propietario = propietarioRepository
                .findById(propietarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Propietario no encontrado"
                        ));

        mascota.setPropietario(propietario);

        return mascotaRepository.save(mascota);
    }


    @Override
    @Transactional
    public Mascota actualizar(
            Long id,
            Mascota datos
    ) {

        Mascota actual = buscarPorId(id);

        actual.setNombre(datos.getNombre());
        actual.setEspecie(datos.getEspecie());
        actual.setRaza(datos.getRaza());
        actual.setEdad(datos.getEdad());
        actual.setPeso(datos.getPeso());

        return mascotaRepository.save(actual);
    }


    @Override
    @Transactional
    public void eliminar(Long id) {

        mascotaRepository.delete(
                buscarPorId(id)
        );
    }


    @Override
    public List<Mascota> buscarPorPropietario(Long propietarioId) {

        return mascotaRepository
                .findByPropietarioId(propietarioId);
    }


    @Override
    @Transactional
    public Mascota asignarVeterinario(Long mascotaId, Long veterinarioId) {

        Mascota mascota =
                buscarPorId(mascotaId);

        Veterinario veterinario = veterinarioRepository
                .findById(veterinarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Veterinario no encontrado"
                        ));

        mascota.getVeterinarios()
                .add(veterinario);

        return mascotaRepository.save(mascota);
    }
}
