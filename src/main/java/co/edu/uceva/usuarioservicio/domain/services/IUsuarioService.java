package co.edu.uceva.usuarioservicio.domain.services;

import co.edu.uceva.usuarioservicio.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interface que define los métodos que se pueden realizar sobre la entidad Usuario
 */
public interface IUsuarioService {
    Usuario save(Usuario usuario);
    void delete(Usuario usuario);
    Usuario findById(Long id);
    Usuario update(Usuario usuario);
    List<Usuario> findAll();
    Page<Usuario> findAll(Pageable pageable);
}