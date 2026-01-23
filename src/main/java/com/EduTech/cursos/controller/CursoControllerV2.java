package com.EduTech.cursos.controller;

import com.EduTech.cursos.assembler.CursoModelAssembler;
import com.EduTech.cursos.model.Curso;
import com.EduTech.cursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/cursos")
public class CursoControllerV2 {

    // --- USAMOS EL SERVICE, NO EL REPOSITORIO ---
    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoModelAssembler cursoAssembler;

    // --- 1. LISTAR TODOS ---
    @GetMapping
    public CollectionModel<EntityModel<Curso>> listarCursos() {
        // Llamamos al service para obtener la lista
        List<EntityModel<Curso>> cursos = cursoService.listarCursos().stream()
                .map(cursoAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cursos,
                linkTo(methodOn(CursoControllerV2.class).listarCursos()).withSelfRel());
    }

    // --- 2. OBTENER POR ID ---
    @GetMapping("/{id}")
    public EntityModel<Curso> obtenerPorId(@PathVariable Long id) {
        Curso curso = cursoService.obtenerPorId(id);

        if (curso == null) {
            throw new RuntimeException("Curso no encontrado con ID: " + id);
        }

        return cursoAssembler.toModel(curso);
    }

    // --- 3. CREAR CURSO (Usando Map -> Service) ---
    @PostMapping
    public ResponseEntity<EntityModel<Curso>> crearCurso(@RequestBody Map<String, Object> payload) {

        String titulo = (String) payload.get("titulo");
        String descripcion = (String) payload.get("descripcion");

        Long idInstructor = Long.valueOf(payload.get("idInstructor").toString());

        Curso nuevoCurso = new Curso();
        nuevoCurso.setTitulo(titulo);
        nuevoCurso.setDescripcion(descripcion);
        nuevoCurso.setInstructorId(idInstructor);

        Curso cursoGuardado = cursoService.guardarCurso(nuevoCurso);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cursoAssembler.toModel(cursoGuardado));
    }

    @PutMapping("/{id}/aprobar")
    public ResponseEntity<EntityModel<Curso>> aprobarCurso(@PathVariable Long id) {
        Curso cursoAprobado = cursoService.aprobarCurso(id);

        return ResponseEntity.ok(cursoAssembler.toModel(cursoAprobado));
    }

    @PutMapping("/{id}/asignar")
    public ResponseEntity<EntityModel<Curso>> asignarInstructor(
            @PathVariable Long id,
            @RequestBody Map<String, Object> payload) {

        if (payload == null || !payload.containsKey("idInstructor")) {
            throw new RuntimeException("Falta idInstructor en el body");
        }

        Long nuevoIdInstructor = Long.valueOf(payload.get("idInstructor").toString());

        Curso cursoActualizado = cursoService.asignarInstructor(id, nuevoIdInstructor);

        return ResponseEntity.ok(cursoAssembler.toModel(cursoActualizado));
    }

    @GetMapping("/instructor/{idInstructor}")
    public CollectionModel<EntityModel<Curso>> listarPorInstructor(@PathVariable Long idInstructor) {
        List<EntityModel<Curso>> cursos = cursoService.listarCursosPorInstructor(idInstructor).stream()
                .map(cursoAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cursos,
                linkTo(methodOn(CursoControllerV2.class).listarPorInstructor(idInstructor)).withSelfRel());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable Long id) {
        if (cursoService.obtenerPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }

        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}