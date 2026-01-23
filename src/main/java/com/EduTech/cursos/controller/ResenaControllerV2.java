package com.EduTech.cursos.controller;

import com.EduTech.cursos.assembler.ResenaModelAssembler;
import com.EduTech.cursos.model.Resena;
import com.EduTech.cursos.service.ResenaService;
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
@RequestMapping("/api/v2/resenas")
public class ResenaControllerV2 {

    @Autowired
    private ResenaService resenaService;

    @Autowired
    private ResenaModelAssembler resenaAssembler;

    @PostMapping
    public ResponseEntity<EntityModel<Resena>> crear(@RequestBody Map<String, Object> payload) {

        Long idUsuario = Long.valueOf(payload.get("idUsuario").toString());
        Long idCurso = Long.valueOf(payload.get("idCurso").toString());
        String comentario = (String) payload.get("comentario");
        Integer calificacion = Integer.valueOf(payload.get("calificacion").toString());

        Resena nuevaResena = new Resena();
        nuevaResena.setComentario(comentario);
        nuevaResena.setCalificacion(calificacion);

        Resena creada = resenaService.agregarResena(idUsuario, idCurso, nuevaResena);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resenaAssembler.toModel(creada));
    }

    @GetMapping("/curso/{idCurso}")
    public CollectionModel<EntityModel<Resena>> listarPorCurso(@PathVariable Long idCurso) {
        List<EntityModel<Resena>> resenas = resenaService.listarPorCurso(idCurso)
                .stream()
                .map(resenaAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(resenas,
                linkTo(methodOn(ResenaControllerV2.class).listarPorCurso(idCurso)).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Resena>> obtenerPorId(@PathVariable Long id) {
        Resena resena = resenaService.obtenerPorId(id);
        return ResponseEntity.ok(resenaAssembler.toModel(resena));
    }
}