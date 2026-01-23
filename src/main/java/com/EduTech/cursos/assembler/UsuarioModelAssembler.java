package com.EduTech.cursos.assembler;

import com.EduTech.cursos.controller.UsuarioControllerV2;
import com.EduTech.cursos.model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {

        EntityModel<Usuario> model = EntityModel.of(usuario);

        model.add(linkTo(methodOn(UsuarioControllerV2.class)
                .obtenerPorId(usuario.getId())).withSelfRel());

        return model;
    }
}