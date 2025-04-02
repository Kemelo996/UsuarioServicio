package co.edu.uceva.usuarioservicio.model.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private long cedula;
    private String contraseña;
    private String correo;

    private String nombreCompleto;
    private String rol;
    private String telefono;
}
