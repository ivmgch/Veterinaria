package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Entity.Propietario;
import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Repository.MascotaRepository;
import com.example.Veterinaria.Repository.PropietarioRepository;
import com.example.Veterinaria.Repository.VeterinarioRepository;
import com.example.Veterinaria.Service.MascotaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;
    private final VeterinarioRepository veterinarioRepository;

    @Override
    public Mascota buscarPorId(Long id) {
        return mascotaRepository.findById(id).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    @Override
    public Mascota guardar(Mascota mascota, Long propietarioId) {
        Propietario propietario = propietarioRepository.findById(propietarioId)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
        mascota.setPropietario(propietario);
        return mascotaRepository.save(mascota);
    }

    @Override
    public Mascota actualizar(Long id, Mascota mascota) {
        Mascota existente = buscarPorId(id);
        existente.setNombre(mascota.getNombre());
        existente.setEspecie(mascota.getEspecie());
        existente.setRaza(mascota.getRaza());
        existente.setEdad(mascota.getEdad());
        existente.setPeso(mascota.getPeso());
        return mascotaRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("Mascota no existe");
        }
        mascotaRepository.deleteById(id);
    }

    @Override
    public List<Mascota> buscarPorPropietario(Long propietarioId) {
        return mascotaRepository.findByPropietarioId(propietarioId);
    }

    @Override
    public Mascota asignarVeterinario(Long mascotaId, Long veterinarioId) {
        Mascota mascota = buscarPorId(mascotaId);
        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

        mascota.getVeterinarios().add(veterinario);
        return mascotaRepository.save(mascota);
    }
}