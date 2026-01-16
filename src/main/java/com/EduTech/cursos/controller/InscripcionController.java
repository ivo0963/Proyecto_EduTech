package com.EduTech.cursos.controller;

import com.EduTech.cursos.model.Inscripcion;
import com.EduTech.cursos.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping
    public Inscripcion inscribir(@RequestParam Long idUsuario, @RequestParam Long idCurso) {
        return inscripcionService.inscribir(idUsuario, idCurso);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Inscripcion> listarPorUsuario(@PathVariable Long idUsuario) {
        return inscripcionService.obtenerInscripcionesPorUsuario(idUsuario);
    }

    // GET http://localhost:8081/inscripciones/curso/1
    @GetMapping("/curso/{idCurso}")
    public List<Inscripcion> listarPorCurso(@PathVariable Long idCurso) {
        return inscripcionService.obtenerInscripcionesPorCurso(idCurso);
    }


    // PUT http://localhost:8081/inscripciones/1/progreso?porcentaje=50.0
    @PutMapping("/{idInscripcion}/progreso")
    public Inscripcion actualizarProgreso(@PathVariable Long idInscripcion, @RequestParam Double porcentaje) {
        return inscripcionService.actualizarProgreso(idInscripcion, porcentaje);
    }
}