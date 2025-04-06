package co.edu.uceva.usuarioservicio.controllers.rest;

import co.edu.uceva.usuarioservicio.domain.exception.*; // Paquete corregido (usuarioServicio)
import co.edu.uceva.usuarioservicio.domain.model.Usuario;
import co.edu.uceva.usuarioservicio.domain.services.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import co.edu.uceva.usuarioservicio.domain.exception.ValidationException;


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

    public UsuarioRestController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> getUsuarios() {
        Map<String, Object> response = new HashMap<>();
        List<Usuario> usuarios = usuarioService.findAll();

        if (usuarios.isEmpty()) {
            throw new NoHayUsuariosException();
        }

        response.put(USUARIOS, usuarios);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuarios/page/{page}")
    public ResponseEntity<Page<Usuario>> getUsuariosPaginados(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Usuario> usuarios = usuarioService.findAll(pageable);

        if (usuarios.isEmpty()) {
            throw new PaginaSinUsuariosException(page);
        }

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Usuario usuario = usuarioService.findById(id);

        if (usuario == null) {
            throw new UsuarioNoEncontradoException(id);
        }

        response.put(USUARIO, usuario);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Usuario usuario, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        Usuario nuevoUsuario = usuarioService.save(usuario);
        response.put(MENSAJE, "Usuario creado con éxito");
        response.put(USUARIO, nuevoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable Long id,
            @Valid @RequestBody Usuario usuario,
            BindingResult result
    ) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        Usuario usuarioActual = usuarioService.findById(id);
        if (usuarioActual == null) {
            throw new UsuarioNoEncontradoException(id);
        }

        usuario.setId(id);
        Usuario usuarioActualizado = usuarioService.update(usuario);

        response.put(MENSAJE, "Usuario actualizado con éxito");
        response.put(USUARIO, usuarioActualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Usuario usuario = usuarioService.findById(id);

        if (usuario == null) {
            throw new UsuarioNoEncontradoException(id);
        }

        usuarioService.delete(usuario);
        response.put(MENSAJE, "Usuario eliminado con éxito");
        return ResponseEntity.ok(response);
    }

}