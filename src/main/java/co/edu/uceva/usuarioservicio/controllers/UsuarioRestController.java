package co.edu.uceva.usuarioservicio.controllers;

import co.edu.uceva.usuarioservicio.model.entities.Usuario;
import co.edu.uceva.usuarioservicio.model.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuario-service")
public class UsuarioRestController {

    private final IUsuarioService usuarioService;

    private static final String MENSAJE = "mensaje";
    private static final String ERROR = "error";
    private static final String USUARIO = "usuario";
    private static final String USUARIOS = "usuarios";

    @Autowired
    public UsuarioRestController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Listar todos los usuarios.
     */
    @GetMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> getUsuarios() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Usuario> usuarios = usuarioService.findAll();
            if (usuarios.isEmpty()) {
                response.put(MENSAJE, "No hay usuarios en la base de datos.");
                response.put(USUARIOS, usuarios);
                return ResponseEntity.ok(response);
            }
            response.put(USUARIOS, usuarios);
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Obtener usuarios con paginación.
     */
    @GetMapping("/usuarios/page/{page}")
    public ResponseEntity<Object> getUsuariosPaginados(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, 4);

        try {
            Page<Usuario> usuarios = usuarioService.findAll(pageable);
            if (usuarios.isEmpty()) {
                response.put(MENSAJE, "No hay usuarios en la página solicitada.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(usuarios);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Obtener usuario por ID.
     */
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = usuarioService.findById(id);
            if (usuario == null) {
                response.put(MENSAJE, "El usuario con ID " + id + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            response.put(USUARIO, usuario);
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Guardar un usuario.
     */
    @PostMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Usuario usuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario nuevoUsuario = usuarioService.save(usuario);
            response.put(MENSAJE, "El usuario ha sido creado con éxito.");
            response.put(USUARIO, nuevoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al insertar el usuario en la base de datos.");
            response.put(ERROR, e.getMostSpecificCause().getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
