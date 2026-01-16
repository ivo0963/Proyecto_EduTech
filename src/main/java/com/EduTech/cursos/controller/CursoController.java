package com.EduTech.cursos.controller;

import com.EduTech.cursos.model.Curso;
import com.EduTech.cursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public Curso crearCurso(@RequestBody Curso curso) {
        return cursoService.guardarCurso(curso);
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    @GetMapping("/instructor/{idInstructor}")
    public List<Curso> listarPorInstructor(@PathVariable Long idInstructor) {
        return cursoService.listarCursosPorInstructor(idInstructor);
    }

    @PutMapping("/{id}/aprobar")
    public Curso aprobarCurso(@PathVariable Long id) {
        return cursoService.aprobarCurso(id);
    }

    @PutMapping("/{idCurso}/asignar/{idInstructor}")
    public Curso asignarInstructor(@PathVariable Long idCurso, @PathVariable Long idInstructor) {
        return cursoService.asignarInstructor(idCurso, idInstructor);
    }

    @DeleteMapping("/{id}")
    public void eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
    }
}