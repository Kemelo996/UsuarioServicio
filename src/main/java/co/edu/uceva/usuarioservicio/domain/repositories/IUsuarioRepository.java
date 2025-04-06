package co.edu.uceva.usuarioservicio.domain.repositories;

import co.edu.uceva.usuarioservicio.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}
