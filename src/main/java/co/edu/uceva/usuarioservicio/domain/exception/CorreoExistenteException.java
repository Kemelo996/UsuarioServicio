package co.edu.uceva.usuarioservicio.domain.exception;

public class CorreoExistenteException extends RuntimeException {
    public CorreoExistenteException(String correo)
    {
        super("El correo '" + correo + "' ya está registrado.");
    }
}
