package com.example.Veterinaria.Repository;

import com.example.Veterinaria.Entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // Al nombrar el método así, Spring Boot automáticamente sabe que debe
    // buscar en la base de datos las mascotas donde el 'propietario_id' coincida.
    List<Mascota> findByPropietarioId(Long propietarioId);

} 