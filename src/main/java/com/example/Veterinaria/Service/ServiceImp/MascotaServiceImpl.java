package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Entity.Propietario;
import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Exception.ResourceNotFoundException;
import com.example.Veterinaria.Repository.MascotaRepository;
import com.example.Veterinaria.Repository.PropietarioRepository;
import com.example.Veterinaria.Repository.VeterinarioRepository;
import com.example.Veterinaria.Service.MascotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con el ID: " + id));
    }

    @Override
    @Transactional
    public Mascota guardar(Mascota mascota, Long propietarioId) {
        Propietario propietario = propietarioRepository.findById(propietarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Propietario no encontrado con el ID: " + propietarioId));

        mascota.setPropietario(propietario);
        return mascotaRepository.save(mascota);
    }

    @Override
    @Transactional
    public Mascota actualizar(Long id, Mascota mascotaDetalles) {
        Mascota mascotaExistente = buscarPorId(id);

        mascotaExistente.setNombre(mascotaDetalles.getNombre());
        mascotaExistente.setEspecie(mascotaDetalles.getEspecie());
        mascotaExistente.setRaza(mascotaDetalles.getRaza());
        mascotaExistente.setEdad(mascotaDetalles.getEdad());
        mascotaExistente.setPeso(mascotaDetalles.getPeso());

        return mascotaRepository.save(mascotaExistente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Mascota mascota = buscarPorId(id);
        mascotaRepository.delete(mascota);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mascota> buscarPorPropietario(Long propietarioId) {
        // 1. Validamos primero que el propietario exista usando el repositorio inyectado
        propietarioRepository.findById(propietarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Propietario no encontrado con el ID: " + propietarioId));
        // 2. Si existe, entonces sí retornamos su lista de mascotas (esté vacía o no)
        return mascotaRepository.findByPropietarioId(propietarioId);
    }

    @Override
    @Transactional
    public Mascota asignarVeterinario(Long mascotaId, Long veterinarioId) {
        Mascota mascota = buscarPorId(mascotaId);
        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado con el ID: " + veterinarioId));
        // 1. Seguro nativo contra nulos por si Hibernate omite instanciar la lista
        if (mascota.getVeterinarios() == null) {
            mascota.setVeterinarios(new ArrayList<>());
        }
        // 2. Blindaje de memoria: Validamos explícitamente usando el ID para evadir el fallo de .contains()
        boolean yaAsignado = mascota.getVeterinarios().stream()
                .anyMatch(v -> v.getId().equals(veterinario.getId()));
        if (!yaAsignado) {
            mascota.getVeterinarios().add(veterinario);
        }
        return mascotaRepository.save(mascota);
    }
}