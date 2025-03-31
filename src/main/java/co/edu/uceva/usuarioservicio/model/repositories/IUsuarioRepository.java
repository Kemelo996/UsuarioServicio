package co.edu.uceva.usuarioservicio.model.repositories;

import co.edu.uceva.usuarioservicio.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
