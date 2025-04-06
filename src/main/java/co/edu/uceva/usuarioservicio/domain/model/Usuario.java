package co.edu.uceva.usuarioservicio.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "La cédula no puede ser nula")
    @Column(unique = true, nullable = false)
    private Long cedula;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Column(nullable = false)
    private String contraseña;

    @NotEmpty(message = "El correo no puede estar vacío")
    @Column(unique = true, nullable = false)
    private String correo;

    @NotEmpty(message = "El nombre completo no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false)
    private String nombreCompleto;

    @NotEmpty(message = "El rol no puede estar vacío")
    @Column(nullable = false)
    private String rol;

    private String telefono;
}
