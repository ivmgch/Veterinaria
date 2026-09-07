package org.example.Repository;

import org.example.Entity.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {

    Optional<HistoriaClinica> findByMascotaId(Long mascotaId);
}
