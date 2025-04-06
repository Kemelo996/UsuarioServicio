package co.edu.uceva.usuarioservicio.domain.exception;

public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException(Long id) {
        super("Ya existe un usuario con ID: " + id);
    }
}
