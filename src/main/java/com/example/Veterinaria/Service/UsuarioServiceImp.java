package com.example.Veterinaria.Service;

import com.example.Veterinaria.Entity.Usuario;
import com.example.Veterinaria.Repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServiceImp implements UsuarioService {

    private final UsuarioRepository usuarioRespository;


    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRespository.save(usuario);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRespository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRespository.findById(id);
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!usuarioRespository.existsById(id)) {
            throw new RuntimeException("Usuario no existe");
        }
        usuarioRespository.deleteAllById(id);
    }
}
