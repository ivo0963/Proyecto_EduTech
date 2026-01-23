package com.EduTech.cursos.assembler;

import com.EduTech.cursos.controller.ResenaControllerV2;
import com.EduTech.cursos.model.Resena;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ResenaModelAssembler implements RepresentationModelAssembler<Resena, EntityModel<Resena>> {

    @Override
    public EntityModel<Resena> toModel(Resena resena) {

        EntityModel<Resena> model = EntityModel.of(resena);

        model.add(linkTo(methodOn(ResenaControllerV2.class)
                .obtenerPorId(resena.getId())).withSelfRel());

        if (resena.getCurso() != null) {
            model.add(linkTo(methodOn(ResenaControllerV2.class)
                    .listarPorCurso(resena.getCurso().getId())).withRel("ver-todas-resenas-curso"));
        }

        return model;
    }
}