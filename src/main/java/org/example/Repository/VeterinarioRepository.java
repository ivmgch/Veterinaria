package org.example.Repository;

import org.example.Entity.Veterinario;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{
    Optional<Veterinario> findByTarjetaProfesional(String tarjetaProfesional);

    List<Veterinario> findByEspecialidadContainingIgnoreCase(String especialidad);
}
