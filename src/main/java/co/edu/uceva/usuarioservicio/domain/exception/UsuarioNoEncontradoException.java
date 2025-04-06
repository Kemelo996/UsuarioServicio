package co.edu.uceva.usuarioservicio.domain.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
        super("El usuario con ID " + id + " no fue encontrado.");
    }
}
