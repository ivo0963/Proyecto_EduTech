package com.EduTech.cursos.controller;

import com.EduTech.cursos.assembler.UsuarioModelAssembler;
import com.EduTech.cursos.model.Usuario;
import com.EduTech.cursos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioControllerV2 {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioAssembler;

    @PostMapping("/registro")
    public ResponseEntity<EntityModel<Usuario>> registrar(@RequestBody Map<String, Object> payload) {

        String nombre = (String) payload.get("nombre");
        String email = (String) payload.get("email");
        String password = (String) payload.get("password");
        String rol = (String) payload.get("rol"); // Puede ser null, el service lo maneja

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(password);
        nuevoUsuario.setRol(rol);

        Usuario registrado = usuarioService.registrar(nuevoUsuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioAssembler.toModel(registrado));
    }

    @PostMapping("/login")
    public ResponseEntity<EntityModel<Usuario>> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Usuario usuario = usuarioService.login(email, password);

        return ResponseEntity.ok(usuarioAssembler.toModel(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuarioAssembler.toModel(usuario));
    }
}