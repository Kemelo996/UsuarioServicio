package co.edu.uceva.usuarioservicio.domain.exception;

public class NoHayUsuariosException extends RuntimeException {
    public NoHayUsuariosException() {
        super("No hay usuarios en la base de datos.");
    }
}
