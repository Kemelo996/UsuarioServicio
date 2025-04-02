package co.edu.uceva.usuarioservicio.model.services;

import co.edu.uceva.usuarioservicio.model.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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