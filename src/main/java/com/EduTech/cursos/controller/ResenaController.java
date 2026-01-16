package com.EduTech.cursos.controller;

import com.EduTech.cursos.model.Resena;
import com.EduTech.cursos.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @PostMapping
    public Resena crearResena(@RequestParam Long idUsuario,
                              @RequestParam Long idCurso,
                              @RequestBody Resena resena) {
        return resenaService.agregarResena(idUsuario, idCurso, resena);
    }

    @GetMapping("/curso/{idCurso}")
    public List<Resena> listarPorCurso(@PathVariable Long idCurso) {
        return resenaService.listarPorCurso(idCurso);
    }
}