package com.example.Veterinaria.Repository;

import com.example.Veterinaria.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    void deleteAllById(Long id);
}
