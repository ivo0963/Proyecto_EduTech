package com.EduTech.cursos.service;

import com.EduTech.cursos.model.Curso;
import com.EduTech.cursos.model.Resena;
import com.EduTech.cursos.model.Usuario;
import com.EduTech.cursos.repository.CursoRepository;
import com.EduTech.cursos.repository.ResenaRepository;
import com.EduTech.cursos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Resena agregarResena(Long idUsuario, Long idCurso, Resena resena) {

        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Curso curso = cursoRepository.findById(idCurso).orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        resena.setUsuario(usuario);
        resena.setCurso(curso);

        return resenaRepository.save(resena);
    }
    public Resena obtenerPorId(Long id) {
        return resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada con ID: " + id));
    }

    public List<Resena> listarPorCurso(Long idCurso) {
        return resenaRepository.findByCursoId(idCurso);
    }
}