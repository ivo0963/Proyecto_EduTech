package com.EduTech.cursos.controller;

import com.EduTech.cursos.model.Curso;
import com.EduTech.cursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Curso curso, @RequestHeader("Rol-Usuario") String rol) {
        if (!"GERENTE".equalsIgnoreCase(rol)) return ResponseEntity.status(403).body("Solo el Gerente puede crear cursos");
        return ResponseEntity.ok(cursoService.guardarCurso(curso));
    }

    @PatchMapping("/{id}/aprobar")
    public ResponseEntity<?> aprobar(@PathVariable Long id, @RequestHeader("Rol-Usuario") String rol) {
        if (!"GERENTE".equalsIgnoreCase(rol)) return ResponseEntity.status(403).body("Acceso denegado");
        return ResponseEntity.ok(cursoService.aprobarCurso(id));
    }

    @PutMapping("/{id}/asignar/{instructorId}")
    public ResponseEntity<?> asignar(@PathVariable Long id, @PathVariable Long instructorId, @RequestHeader("Rol-Usuario") String rol) {
        if (!"GERENTE".equalsIgnoreCase(rol)) return ResponseEntity.status(403).body("Acceso denegado");
        return ResponseEntity.ok(cursoService.asignarInstructor(id, instructorId));
    }

    @GetMapping
    public List<Curso> listar() {
        return cursoService.listarCursos();
    }
}